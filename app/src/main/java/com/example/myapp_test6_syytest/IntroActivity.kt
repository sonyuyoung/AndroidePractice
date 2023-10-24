package com.example.myapp_test6_syytest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //화면실행시 최초1회실행
        super.onCreate(savedInstanceState)
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3초 있다 메인액티비티로


        setContentView(R.layout.activity_intro)
        // 실제로 화면에 출력 하는 함수 . ( 출력담당 )

    }
}
