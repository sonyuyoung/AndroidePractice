package com.example.myapp_test6_syytest.ch11_12_Test.viewPageandrecyclerview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test6_syytest.databinding.FragmentTest1Binding


class Test1Fragment : Fragment() {
    lateinit var binding :FragmentTest1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentTest1Binding.inflate(layoutInflater)


        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTest1Binding.inflate(layoutInflater,container,false)
        return binding.root
    }   }