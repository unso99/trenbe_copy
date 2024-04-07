package com.myshop_application.model

import android.net.http.UrlRequest.Status

data class Member(
    val email : String,
    val password : String,
    val name : String,
    val phone : String,
    val nickname : String,
    val birth : Int
)

data class MemberResponse(
    val dto : Member,
    val list : List<Member>,
    val status : String
)
