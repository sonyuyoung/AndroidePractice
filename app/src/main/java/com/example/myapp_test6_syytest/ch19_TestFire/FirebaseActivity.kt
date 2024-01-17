package com.example.myapp_test6_syytest.ch19_TestFire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityFirebaseBinding

class FirebaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirebaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}