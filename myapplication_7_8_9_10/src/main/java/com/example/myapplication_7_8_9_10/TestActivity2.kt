package com.example.myapplication_7_8_9_10

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_7_8_9_10.databinding.ActivityTest2Binding

class TestActivity2 : AppCompatActivity() {
    lateinit var activityTest2Binding: ActivityTest2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test2)
        activityTest2Binding = ActivityTest2Binding.inflate(layoutInflater)
        setContentView(activityTest2Binding.root)

        activityTest2Binding.frameBtn.setOnClickListener {
            activityTest2Binding.frameBtn.visibility= View.VISIBLE

        }
        activityTest2Binding.img1.setOnClickListener{
            activityTest2Binding.frameBtn.visibility=View.VISIBLE
            activityTest2Binding.img1.visibility=View.INVISIBLE
        }

    }
}