package com.example.myapp_test6_syytest.TabTest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityTabTest2Binding
import com.google.android.material.tabs.TabLayoutMediator

class TabTest2Activity : AppCompatActivity() {

    lateinit var binding: ActivityTabTest2Binding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //탭 레이아웃과 뷰페이저 2연동 !
        val tabLayout = binding.tabs2
        val viewPager = binding.viewPagerTab
//           인덱스 = 포지션
    viewPager.adapter = ViewPagerAdapterTest(this)

        TabLayoutMediator(tabLayout,viewPager){
            tab,position -> tab.text = "Tab${position+1}"

        }.attach()
    }


}