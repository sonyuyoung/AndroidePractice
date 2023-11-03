package com.example.myapp_test6_syytest.ch17_Test.preferenceTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivitySharedPreferDetailTestBinding


class SharedPreferDetailTestActivity : AppCompatActivity() {
    //2번
    // 공유프리퍼런스 파일에 ㅔ이터 가져오기
    lateinit var binding: ActivitySharedPreferDetailTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferDetailTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 조회버튼누르면 공유된 프리퍼런스에 파일에 저장된 값 가져오기
        binding.getSheardPrefer.setOnClickListener {
            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val email = pref.getString("email", "Default EMAIL")
            val password = pref.getString("password", "Default PASSWORD")
            val pickRadio = pref.getString("pickRadio", "Default pickRadio")
            val email2 = pref.getString("email2", "Default EMAIL")
            val password2 = pref.getString("password2", "Default PASSWORD")
            val email3 = pref.getString("email3", "Default EMAIL")
            val password3 = pref.getString("password3", "Default PASSWORD")
            // 해당 텍스트 뷰에 넣기
            binding.resultEmailSP.text = email
            binding.resultPasswordSP.text = password
            binding.resultRadioSP.text = pickRadio

            binding.deleteSharedPreferBtnTest.setOnClickListener{
                val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = pref.edit()
                editor.remove("email")
                editor.remove("password")
                editor.commit()

            }
            binding.deleteFilePreferBtnTest.setOnClickListener{
                val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = pref.edit()
                editor.clear()
                editor.commit()
            }

        }


    }
}