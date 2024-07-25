package com.neilsayok.musewearables.data.model


data class ErrorEventData(
    var code: String?,
    var url: String?,
    var errorMsg: String?,
    var errorTitle: String?,
)

data class ErrorData(
    val errors: List<ErrorsList> = emptyList(),
)

data class ErrorsList(
    val message: String?,
    val property: String?,
    val code: String?,
    val rawErrors: List<RawError?>?,
    val title: String?
)

data class RawError(
    val error: String?,
    val message: String?,
    val description: String?,
    val code: String?
)

data class ErrorTitleMsg(
    val errorTitle: String?,
    val errorCode: String?,
    val message: String?
)
