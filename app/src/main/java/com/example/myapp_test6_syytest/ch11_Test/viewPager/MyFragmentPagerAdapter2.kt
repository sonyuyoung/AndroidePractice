package com.example.myapp_test6_syytest.ch11_Test.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp_test6_syytest.ch11_Test.viewPager.fragment.SampleFragment4
import com.example.myapp_test6_syytest.ch11_Test.viewPager.fragment.SampleFragment5

class MyFragmentPagerAdapter2( fragmentActivity : FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    var sampleFragment2 : List<Fragment>
    init{
        sampleFragment2 = listOf(SampleFragment4(), SampleFragment5())
    }
    override fun getItemCount(): Int = sampleFragment2.size
    override fun createFragment(position: Int): Fragment {
        val returnFrament : Fragment = sampleFragment2[position]
        return  returnFrament
    }

}
