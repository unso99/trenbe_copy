package com.myshop_application.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myshop_application.model.Member

class PreferenceUtil(context : Context) {

    private val preferences : SharedPreferences = context.getSharedPreferences("member", Context.MODE_PRIVATE)

    fun saveMember(key : String, member : Member) {
        val gson = Gson()
        val json = gson.toJson(member)
        preferences.edit().putString(key,json).apply()
    }

    fun saveToken(key: String, token : String){
        preferences.edit().putString(key,token).apply()
    }

    fun getToken(key: String) =preferences.getString(key,null)

    fun getMember(key: String) : Member?{
        val json = preferences.getString(key,"")
        json?.let{
            val gson = Gson()
            val type = object :TypeToken<Member>() {}.type
            return gson.fromJson(json,type)
        }
        return null
    }
}