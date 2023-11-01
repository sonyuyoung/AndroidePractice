package com.example.myapp_test6_syytest.ch13_1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityTestBundleBinding

class TestBundleActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestBundleBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTestBundleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("syy", "onCreate()")

        // 화면이 회전시 데이터 손실이 되는데 ㅇ미시저장소 번들에 저장하고 값을가져오기
        // 앱을 사용중에는 기본값들을 세터및 게터가 용이하고,앱이종료되면 휘발정이 단점이다
        // 비휘발성인 실제 내부저장소를 사용할 예정이고 1)파일, 2) 데이터베이스(SQLLite 3) 외부디비사용 예정
        binding.countBtn.setOnClickListener {
            count++
            binding.resultText.text = "$count"
        }
    //oncreate 마지막부분
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("syy", "onSaveInstanceState()")
        // 데이터 세터 , ondestroy() 전에 저장
        outState.putString("data1","hellow~~")
        outState.putInt("data2",20)
        outState.putInt("data3",count)
    }


    // 임시저장... 소..?

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("syy", "onRestoreInstanceState()")
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")
        val data3 = savedInstanceState.getInt("data3")
        binding.resultText.text="$data1 - $data2"
        binding.resultText2.text="카운트 임시저장 : $data3"

    }
    override fun onStart() {
        super.onStart()
        Log.d("syy", "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("syy", "onResume()")
    }
    override fun onPause() {
        super.onPause()
        Log.d("syy", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("syy", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("syy", "onDestroy()")
    }

}