package com.example.myapp_test6_syytest.ch11_12_Test.viewPager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test6_syytest.databinding.FragmentSample5Binding

class SampleFragment5 : Fragment() {
lateinit var binding : FragmentSample5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentSample5Binding.inflate(layoutInflater)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSample5Binding.inflate(layoutInflater)
        return binding.root
    }
}