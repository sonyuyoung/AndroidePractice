package com.example.myapp_test6_syytest.ch11_Test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test6_syytest.databinding.FragmentFourBinding

class FourFragment : Fragment() {
    lateinit var binding : FragmentFourBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFourBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    }