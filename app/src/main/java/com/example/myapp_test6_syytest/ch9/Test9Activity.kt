package com.example.myapp_test6_syytest.ch9

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapp_test6_syytest.LoginFormActivity
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.databinding.ActivityTest9Binding


class Test9Activity : AppCompatActivity() {
    lateinit var activityTest9Activity: ActivityTest9Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_test9)*/
        activityTest9Activity = ActivityTest9Binding.inflate(layoutInflater)
        setContentView(activityTest9Activity.root)

        activityTest9Activity.testImg.setOnClickListener {
            Toast.makeText(this@Test9Activity, "이미지 클릭되었음", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Test9Activity, LoginFormActivity::class.java)
            startActivity(intent)
        }
        //소스코드로 정적자원 사용하기 문자열
        //2번
        activityTest9Activity.textView2.text = getString(R.string.app_intro)
        // 해당라이브러리의기능을 확인하는부분보다
        //sdk 버전에 따라서 라이브러리 패키지명 등 다른부분에 집중
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            Log.d(
                "Syy",
                "width:${windowMetrics.bounds.width()},height:${windowMetrics.bounds.height()}"
            )

        } else {
            val display = windowManager.defaultDisplay
        }
        //허가 확인여부 테스트
//        android.permission.ACCESS_FINE_LOCATION
        val status = ContextCompat.checkSelfPermission(
            this@Test9Activity,
            "android.permission.ACCESS_FINE_LOCATION"
        )
        if (status == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this@Test9Activity, "위치권한 승인됨", Toast.LENGTH_SHORT).show()
            Log.d("syy", "권한이 승인안됨: ${status}")
        }

    }
}