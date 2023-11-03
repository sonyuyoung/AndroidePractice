package com.example.myapp_test6_syytest.ch17_Test.preferenceTest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivitySharedPreferTestBinding


class SharedPreferTestActivity : AppCompatActivity() {
    //1번화면
    // val text = 공유프리퍼런스 파일에 데이터 담기
    //공유프리퍼런스 모든 액티비티 화면에서 접근이 가능함 .
    // EditText 뷰에서
    // 라디오 뷰에서 ,
    // 각 뷰에서 , 데이터를 가져와서 , 회원가입하듯
    //  text 에 저장
    lateinit var binding: ActivitySharedPreferTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이벤트 처리할 예정.
        // 회원가입에서 사용했던 뷰를 재사용.
        binding.buttonInsertSP.setOnClickListener {
            val email = binding.userEmail.text.toString()
            val password = binding.userPwd.text.toString()
            val pickRadio = getValue(binding.testRadioGroup)
            // 라디오 잠시 대기.

            // 공유프리퍼런스 값 넣기. 저장.
            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("email",email)
            editor.putString("password",password)
            editor.putString("pickRadio",pickRadio)
            editor.putString("email2",email)
            editor.putString("password2",password)
            editor.putString("email3",email)
            editor.putString("password3",password)
            editor.commit()

            //2번 화면으로 이동.
            val intent = Intent(this, SharedPreferDetailTestActivity::class.java)
            startActivity(intent)
        }
    }

    // 함수의 매개변수에 , 라디오 그룹을 넣었음 . 참고 ) 1. 라디오그룹 , 2) 라디오 요소 구성
    fun getValue(v: View?): String? {
        val male = binding.radio1
        val female = binding.radio2
        var pickValue: String? = null

        if (male.isChecked) {
            pickValue = male.text.toString()
        } else if (female.isChecked) {
            pickValue = female.text.toString()
        }
        return pickValue
    }

}
