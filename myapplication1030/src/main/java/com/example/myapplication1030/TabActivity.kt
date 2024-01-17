package com.example.myapplication1030

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication1030.adapter.RecyclerViewTest
import com.example.myapplication1030.databinding.ActivityTabBinding

//리사이클러뷰 아이템더보기
class TabActivity : AppCompatActivity() {
    lateinit var binding : ActivityTabBinding
    var newDataNumber = 11
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //리사이클러뷰 붙이기
        //준비물 : 1)리사이클러뷰 어댑터 2)목록요소의 아이템뷰 3)더미데이터
        val datas = mutableListOf<String>()
        for (i in 1..10)
            datas.add("더미데이터 추가번호$i")

        //출력 수직
        val layoutManager = LinearLayoutManager(this)


        //리사이클러 뷰 속성 옵션에 출력옵션 붙이기 ! //)
        binding.recyclerViewTest.layoutManager = layoutManager
        binding.recyclerViewTest.adapter = RecyclerViewTest(datas)


        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerViewTest.adapter = customAdapter // 어댑터 설정

        binding.addBtn.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

        binding.delBtn.setOnClickListener {
            datas.removeAt(datas.size - 1)
            customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
        }
    }
}