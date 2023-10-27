package com.example.myapp_test6_syytest.ch11_Test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6_syytest.ch11_Test.recycler.MyAdapter
import com.example.myapp_test6_syytest.databinding.ActivityTest11RecyclerViewBinding


// 프레그먼트건 스크롤뷰건 리사이클러건 반드시 흰색도화지 처럼 베이스가 있어야함
class Test11_RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityTest11RecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTest11RecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 더미로 사용한 텍스트 ..!  나중에는 데이터를 가지고와서 바인딩할것임
        var datas = mutableListOf<String>()
        for (i in 1..10) {
            datas.add(" 밥그릇 $i ")
        }
        //기본 값으로 세로방향 출력
        //binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // 가로출력
        var layoutManager = LinearLayoutManager(this)
        // 담아두고
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        //recyclerView 해당 .. 수평으로 넣기
        binding.recyclerView.layoutManager = layoutManager
       //액티비티 - > 리사이클러 뷰 -> 실제데이터를 연결하는 부분
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        //더미 데이터를 넣음 아까 그 10개 반복한 밥그릇 datas
        //필요한 데이터들만 ㄷ뽑아내는것  : 정제
        binding.recyclerView2.adapter = MyAdapter(datas)

        binding.recyclerView2.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


        // recyclerView3 가로로 출력
        var layoutManager2 = LinearLayoutManager(this)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView3.layoutManager = layoutManager2
        binding.recyclerView3.adapter = MyAdapter(datas)
        binding.recyclerView3.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    }
}