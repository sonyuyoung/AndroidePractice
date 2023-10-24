package com.example.myapp_test6_syytest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityJoinBinding


class JoinActivity : AppCompatActivity() {
    lateinit var activityJoinBinding: ActivityJoinBinding
    lateinit var imgCheck:String
    lateinit var TAG:String
    override fun onCreate(savedInstanceState: Bundle?) {
        imgCheck = "Y"
        TAG = "JoinActivity"

        super.onCreate(savedInstanceState)
        activityJoinBinding = ActivityJoinBinding.inflate(layoutInflater)
        //뷰바인딩 3번
        setContentView(activityJoinBinding.root)

        activityJoinBinding.imageView.setOnClickListener {
            Toast.makeText(this@JoinActivity,"이미지 버튼 클릭해주세요",Toast.LENGTH_SHORT).show()
        }

        // 라디오 버튼 체크 확인.
        activityJoinBinding.radio1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@JoinActivity,"남자 클릭됨",Toast.LENGTH_SHORT).show()
        }

        activityJoinBinding.radio2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@JoinActivity,"여자 클릭됨",Toast.LENGTH_SHORT).show()
        }
        // 버튼으로 , 뷰 show/hide 해보기.
        val imgText : Button = findViewById(R.id.imgText)
        imgText.setOnClickListener {
            Log.d(TAG,"버튼을 클릭시 확인.")
            if(imgCheck.equals("Y")) {
                val imgView: ImageView = findViewById(R.id.imageView)
                imgView.visibility = View.VISIBLE
                imgCheck = "N"
            } else {
                val imgView: ImageView = findViewById(R.id.imageView)
                imgView.visibility = View.GONE
                imgCheck = "Y"
            }
        }

        // 클릭시 이벤트 리스너 이용해서, 해당 화면으로 이동.
//        loginBtn.setOnClickListener {
//            val intent = Intent(this@MainActivity, LoginFormActivity::class.java)
//            startActivity(intent)
//        }
        // viewBinding 기술을 이용해서, 좀더 쉽게 뷰를 선택하는 방법 이용해서, 해당 화면으로 이동하기.

        // 프레임 레이아웃에서, 버튼과, 이미지를 클릭 이벤트를 이용해서 show/hide ,
//        activityTestBinding.frameBtn.setOnClickListener {
//            activityTestBinding.frameBtn.visibility = View.INVISIBLE
//            activityTestBinding.img1.visibility = View.VISIBLE
//        }
//
//        activityTestBinding.img1.setOnClickListener {
//            activityTestBinding.frameBtn.visibility = View.VISIBLE
//            activityTestBinding.img1.visibility = View.INVISIBLE
//        }

    }
    }
