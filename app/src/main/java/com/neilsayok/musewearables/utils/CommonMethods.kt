package com.neilsayok.musewearables.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.gson.Gson
import com.neilsayok.musewearables.BuildConfig
import com.neilsayok.musewearables.R
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.constants.SOMETHING_WENT_WRONG
import com.neilsayok.musewearables.data.error.ErrorData
import com.neilsayok.musewearables.data.error.ErrorEventData
import com.neilsayok.musewearables.data.error.ErrorTitleMsg
import retrofit2.HttpException

fun getErrorTitleFromResponse(errorObj: String?): ErrorTitleMsg? {
    return try {
        if (!errorObj.isNullOrEmpty()) {
            val gson = Gson()
            val error: ErrorData = gson.fromJson(errorObj, ErrorData::class.java)
            val response: ErrorTitleMsg? = if (!error.errors.isNullOrEmpty()) {
                val rawErrorMsg = if (error.errors[0].rawErrors != null) {
                    if (BuildConfig.FLAVOR != "prod") {
                        "${error.errors[0].rawErrors?.get(0)?.message.toString()} \n${
                            error.errors[0].rawErrors?.get(0)?.description?:""
                        }"
                    } else {
                        error.errors[0].rawErrors?.get(0)?.message.toString()
                    }
                } else {
                    error.errors[0].message
                }
                ErrorTitleMsg(
                    errorTitle = error.errors[0].property ?: EMPTY_STRING,
                    message = rawErrorMsg,
                    errorCode = error.errors[0].code ?: SOMETHING_WENT_WRONG
                )
            } else {
                null
            }
            response
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

fun getErrorEventData(
    exception: HttpException,
    pinCode: String?,
    message: String?,
    errorTitle: String?,
): ErrorEventData {
    return ErrorEventData(
        (exception).response()?.code().toString(),
        (exception).response()?.raw()?.request?.url.toString(),
        message,
        errorTitle,
    )
}

fun backgroundThreadShortToast(
    context: Context?,
    msg: String?,
) {
    if (context != null && msg != null) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(
                context, msg, Toast.LENGTH_SHORT
            ).show()
        }
    }
}

fun showToast(
    context: Context?,
    msg: String?,
    duration: Int = Toast.LENGTH_SHORT
) {
    try {
        Toast.makeText(context, msg, duration).show()
    } catch (e: Exception) {
        backgroundThreadShortToast(context, msg)
    }

}

@Composable
fun loadImage(
    url: String?, fallBackImage: Painter = painterResource(R.drawable.ic_image_load_failed)
) = if (url.isNullOrEmpty()) {
    fallBackImage
} else {
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(data = url)
            .decoderFactory(SvgDecoder.Factory()).allowHardware(false).build(),
        error = fallBackImage,
        fallback = fallBackImage,
        //placeholder = painterResource(id = R.drawable.download)
    )
}