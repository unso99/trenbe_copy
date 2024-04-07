package com.myshop_application.service

import com.myshop_application.model.Member
import com.myshop_application.model.MemberResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {
    @POST("member/post-member")
    suspend fun addMember(@Body body: Member): Response<MemberResponse>
}