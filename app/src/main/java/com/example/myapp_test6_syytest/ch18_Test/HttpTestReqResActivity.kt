package com.example.myapp_test6_syytest.ch18_Test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityHttpTestReqResBinding

class HttpTestReqResActivity : AppCompatActivity() {
    lateinit var binding : ActivityHttpTestReqResBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHttpTestReqResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // reqres.in : 외국에서 무료 rest테스트 서버 제공 해주는ㄴ곳
        // 데이터를 받아서 리사이클러뷰 출력
        // 리사이클러뷰 1) 어댑터 2) 휴홀더 3) 목록요소의 뷰 4) 데이터



        // http 통신 준비물
        // 1) Myapplication 설정파일 2) 인터페이스 3) 모델 또는 모델이 담겨진 리스트 필요 .
        // 준비작업 1) 모델준비하기
        // 준비작업 2) 모델을 요소로 하는 리스트로 준비하기
        // 주의사항.https://reqres.in/api/users?page=2
    } //onCreate


}