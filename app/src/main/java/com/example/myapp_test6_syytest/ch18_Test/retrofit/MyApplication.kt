package com.example.myapp_test6_syytest.ch18_Test.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 용도는 자주 사용이될 네트워크 인터페이스를 미리 시스템에 등록
// 매니 페스트 등록 해야힘   <application android:name=".ch18_Test.retrofit.MyApplication"
// 앱이 실행이 되면 MyApplication 의 기능이 메모리 등록이 되어서 사용하기편함
class MyApplication:Application(){
//    1) 통신에 필요한 인스턴스를 선언 및 초기화
//    2) 통신할서버의 URL 주소를등록함


    // 초기화필요
    val networkService : INetworkservice
    val retrofit :Retrofit

        // 초기화
    get() = Retrofit.Builder()
            //레스트 통신할 상대방 서버
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        // 내가정의한필요한 함수들만.. 가져올것 .. 잉 ..
        networkService = retrofit.create(INetworkservice::class.java)
    }
}