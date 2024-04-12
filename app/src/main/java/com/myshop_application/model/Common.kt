package com.myshop_application.model

data class Common(
    val code_id : Int? = null,
    val p_code_id : Int? = null,
    val code_name : String? = null,
    val code_value : String? = null
)

data class CommonResponse(
    val dto : Common,
    val list : List<Common>,
    val error : String
)
