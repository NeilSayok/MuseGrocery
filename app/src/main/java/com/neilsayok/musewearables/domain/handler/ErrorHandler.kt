package com.neilsayok.musewearables.domain.handler

import android.os.Build
import android.util.Log
import com.neilsayok.musewearables.BuildConfig
import com.neilsayok.musewearables.application.MainApplication
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.constants.SOMETHING_WENT_WRONG
import com.neilsayok.musewearables.data.error.ErrorEventData
import com.neilsayok.musewearables.data.error.Resource
import com.neilsayok.musewearables.data.error.toError
import com.neilsayok.musewearables.utils.backgroundThreadShortToast
import com.neilsayok.musewearables.utils.getErrorEventData
import com.neilsayok.musewearables.utils.getErrorTitleFromResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.concurrent.atomic.AtomicBoolean

fun <T> coroutineExceptionHandlerWithFlow(
    scope: CoroutineScope,
    vararg flows: MutableStateFlow<Resource<T>?>,
    errorData: MutableStateFlow<Resource<ErrorEventData?>?>,
    isToastDisplayed: AtomicBoolean? = null,
    forceToast: Boolean = false,
    isMainApi: Boolean = false,
) = CoroutineExceptionHandler { _, exception ->
    scope.launch {
        if (BuildConfig.FLAVOR != "prod") {
            Log.e(
                "ExceptionHandler",
                "${exception.message.toString()}, ${exception.printStackTrace()} ${exception}"
            )
        }



        if (exception is HttpException) {

            val errorResponse = getErrorTitleFromResponse(
                (exception).response()?.errorBody()?.string()
            )



            errorData.apply {
                emit(
                    Resource.success(
                        getErrorEventData(
                            exception,
                            EMPTY_STRING,
                            errorResponse?.message.toString(),
                            errorResponse?.errorTitle.toString()
                        )
                    )
                )
                if (isMainApi) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        @Suppress("Since15")
                        isToastDisplayed?.plain = true
                    } else {
                        isToastDisplayed?.set(true)
                    }
                }
            }

            flows.forEach { liveData ->
                liveData.apply {
                    emit(
                        value.toError(
                            message = errorResponse?.message,
                            errorTitle = errorResponse?.errorTitle,
                            errorCode = errorResponse?.errorCode,
                            handled = forceToast
                        )
                    )
                }
            }


            if (errorResponse != null
                && isToastDisplayed != null
                && !errorResponse.message.isNullOrBlank()
                && !isMainApi
            ) {
                if (!isToastDisplayed.get()) {
                    backgroundThreadShortToast(
                        MainApplication.INSTANCE.applicationContext, errorResponse.errorCode.toString()+" | "+  errorResponse.message.toString()
                    )
                    isToastDisplayed.set(true)
                }
            } else if (forceToast) {
                backgroundThreadShortToast(
                    MainApplication.INSTANCE.applicationContext, errorResponse?.errorCode.toString()+" | "+  errorResponse?.message.toString()
                )
            }


        } else {
            flows.forEach { liveData ->
                liveData.apply {
                    emit(
                        value.toError(
                            message = exception.message ?: SOMETHING_WENT_WRONG
                        )
                    )
                }
            }

        }
    }

}
