package com.example.myapp_test6_syytest.ch11_12_Test.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp_test6_syytest.ch11_12_Test.viewPager.fragment.SampleFragment1
import com.example.myapp_test6_syytest.ch11_12_Test.viewPager.fragment.SampleFragment2
import com.example.myapp_test6_syytest.ch11_12_Test.viewPager.fragment.SampleFragment3


// 뷰페이저의 데이터를 연결시켜주는 프레그먼트 형식의 어댑터
class MyFragmentPagerAdapter( fragmentActivity : FragmentActivity ):FragmentStateAdapter(fragmentActivity) {
    var sampleFragment : List<Fragment>
    init{
        sampleFragment = listOf(SampleFragment1(), SampleFragment2(), SampleFragment3())
    }
    override fun getItemCount(): Int = sampleFragment.size
    override fun createFragment(position: Int): Fragment {
        val returnFrament : Fragment = sampleFragment[position]
        return  returnFrament
        }
    }

