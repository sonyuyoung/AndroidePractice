package com.example.myapp_test6_syytest.ch11_Test.viewPageandrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6_syytest.ch11_Test.viewPageandrecyclerview.adapter.RecyclerViewTest
import com.example.myapp_test6_syytest.ch11_Test.viewPageandrecyclerview.adapter.ViewPagerAdapterTest
import com.example.myapp_test6_syytest.databinding.ActivityTestPageRecyclerBinding

class TestPageRecyclerActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestPageRecyclerBinding
    var newDataNumber = 11
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPageRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뷰페이저와 리사이클러뷰 2개를붙일예정
        //준비물 : 프레그먼트 형식 어댑터 , 조각인 목록요소격 프래그먼트3가지
        //더미데이터
        binding.viewPager1.adapter= ViewPagerAdapterTest(this)

        //리사이클러뷰 붙이기
        //준비물 : 1)리사이클러뷰 어댑터 2)목록요소의 아이템뷰 3)더미데이터
        val datas = mutableListOf<String>()
        for(i in 1..10)
        datas.add("더미데이터 추가번호$i")

        //출력 수직
        val layoutManager = LinearLayoutManager(this)


        //리사이클러 뷰 속성 옵션에 출력옵션 붙이기 ! //)
        binding.recyclerViewTest.layoutManager=layoutManager
        binding.recyclerViewTest.adapter=RecyclerViewTest(datas)


        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerViewTest.adapter = customAdapter // 어댑터 설정

        binding.addBtn.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

        binding.addBtn2.setOnClickListener {
            datas.removeAt(datas.size - 1)
            customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
        }



    }}