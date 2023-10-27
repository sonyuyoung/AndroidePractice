package com.example.myapp_test6_syytest.ch11_Test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test6_syytest.databinding.FragmentOneBinding


class OneFragment : Fragment() {
        lateinit var binding : FragmentOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("syy","생명주기,onCreate")
    }
    override fun onStart() {
        super.onStart()
        Log.d("syy","생명주기,onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("syy","생명주기,onResume")
    }


    override fun onStop() {
        super.onStop()
        Log.d("syy","생명주기,onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("syy","생명주기,onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d("syy","생명주기,onPause")
    }
            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 인스턴스화
        binding = FragmentOneBinding.inflate(layoutInflater,container,false)
        return binding.root
    }}

//    companion object {
//
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            OneFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}