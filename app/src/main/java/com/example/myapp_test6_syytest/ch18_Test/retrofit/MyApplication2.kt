package com.example.myapp_test6_syytest.ch18_Test.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 용도는 자주 사용이될 네트워크 인터페이스를 미리 시스템에 등록
// 매니 페스트 등록 해야힘   <application android:name=".ch18_Test.retrofit.MyApplication"
// 앱이 실행이 되면 MyApplication 의 기능이 메모리 등록이 되어서 사용하기편함
class MyApplication2:Application(){

        val QUERY = "travel"
        val QUERY2 = "Apple"
        val from ="2023-05-22"
        val sortBy ="popularity"
        val API_KEY = "87af28a1123a4fcc9c869c0b81bd243c"
        val BASE_URL = "https://newsapi.org"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"

        
        var networkService: NetworkService
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

