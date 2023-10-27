package com.example.myapp_test6_syytest.ch11_Test.viewPager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.ch11_Test.recycler.MyAdapter2
import com.example.myapp_test6_syytest.databinding.ActivityTestViewPagerBinding

class TestViewPagerActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //더미 재사용
        val testDataSet = ArrayList<String>()
        for (i in 0..5) {
            testDataSet.add("TEST DATA$i")
        }
        // 외부기능이니 디펜던시를 추가해야함 .
        // 뷰페이저 방식 1) 어댑터 사용방법 2) 프래그 먼트 이용
        // 뷰에 코드 추가함 viewPager1


        // 어댑터 구조는 똑같은데
//        binding.viewPager1.adapter = MyAdapter(testDataSet)
        binding.viewPager1.adapter = MyAdapter2(testDataSet)

        //방법의 2번
        binding.viewPager2.adapter = MyFragmentPagerAdapter(this)


    }
}