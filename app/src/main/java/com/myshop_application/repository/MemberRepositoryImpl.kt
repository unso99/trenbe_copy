package com.myshop_application.repository

import com.myshop_application.RetrofitManager
import com.myshop_application.model.Member
import com.myshop_application.model.MemberResponse
import retrofit2.Response

class MemberRepositoryImpl : MemberRepository{
    override suspend fun addMember(dto: Member): Response<MemberResponse> {
        return RetrofitManager.memberService.addMember(dto)
    }

    override suspend fun login(dto: Member): Response<MemberResponse> {
        return RetrofitManager.memberService.login(dto)
    }
}