package com.example.myapp_test6_syytest.ch18_Test.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 용도는 자주 사용이될 네트워크 인터페이스를 미리 시스템에 등록
// 매니 페스트 등록 해야힘   <application android:name=".ch18_Test.retrofit.MyApplication"
// 앱이 실행이 되면 MyApplication 의 기능이 메모리 등록이 되어서 사용하기편함
class MyApplication3:Application(){


        val BASE_URL = "https://apis.data.go.kr/6260000/"


        
        var networkService: NetworkService3
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService3::class.java)
        }
    }

