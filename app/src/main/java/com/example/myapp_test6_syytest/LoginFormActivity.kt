package com.example.myapp_test6_syytest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityLoginFormBinding

class LoginFormActivity : AppCompatActivity() {
    lateinit var activityLoginFormBinding: ActivityLoginFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityLoginFormBinding = ActivityLoginFormBinding.inflate(layoutInflater)

        setContentView(activityLoginFormBinding.root)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        val backBtn:Button = findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent : Intent = Intent(this,ActivityLoginFormBinding::class.java)
            startActivity(intent)
        }
    }
}