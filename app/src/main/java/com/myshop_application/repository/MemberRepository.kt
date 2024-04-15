package com.myshop_application.repository

import com.myshop_application.model.Member
import com.myshop_application.model.MemberResponse
import retrofit2.Response

interface MemberRepository {
    suspend fun addMember(dto : Member) : Response<MemberResponse>
    suspend fun login(dto : Member) : Response<MemberResponse>
    suspend fun getMember(dto:Member) : Response<MemberResponse>
}