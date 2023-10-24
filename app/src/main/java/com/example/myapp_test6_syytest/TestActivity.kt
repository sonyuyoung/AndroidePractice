package com.example.myapp_test6_syytest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    //뷰바인딩 1번
    lateinit var activityTestBinding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        // 뷰바인딩 2번
        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)
        //뷰바인딩 3번
        setContentView(activityTestBinding.root)
        // showBtn 버튼 클릭시 profileImg 보이게
        //특정 뷰를 선택하는 방법 2가지
        // findViewById viewBinding
        //   hideBtn  클릭시 프로필이미지 profileImg 사라짐


        //1번방법
        //1-2번
//        val showBtn: Button = findViewById(R.id.showBtn)
        //2-1번
        val showBtn2 = activityTestBinding.showBtn2
        showBtn2.setOnClickListener {
            activityTestBinding.profileImg.visibility=View.VISIBLE
        }


        //1-2
//        showBtn.setOnClickListener {
//            val profileImg : ImageView = findViewById(R.id.profileImg)
//            profileImg.visibility = View.VISIBLE
//            //  변수 : 타입
//        }
        //1-3
        //1-2번
//        val hideBtn: Button = findViewById(R.id.hideBtn)
//        //1-2
//        hideBtn.setOnClickListener {
//            val profileImg : ImageView = findViewById(R.id.profileImg)
//            profileImg.visibility = View.INVISIBLE
//            // INVISIBLE /GONE
//        }

        //2-1번
        val hideBtn2 = activityTestBinding.hideBtn2
        hideBtn2.setOnClickListener {
            activityTestBinding.profileImg.visibility = View.INVISIBLE
        }

//        val profileImgView : ImageView = findViewById(R.id.profileImg)
//        profileImgView.setOnClickListener{
//            Toast.makeText(this@TestActivity,"이미지 클릭 했다!",Toast.LENGTH_SHORT).show()
//            // 1)현재 컨텍스트 TestActivity , 2)메세지 , 3)메세지 출력시간 3초(LENGTH_SHORT)/LENGTH_LONG
//        }
       activityTestBinding.profileImg.setOnClickListener{
           Toast.makeText(this@TestActivity,"이미지 클릭 했다!",Toast.LENGTH_SHORT).show()
       }

        //체크박스 클릭시 이벤트 핸들러
        activityTestBinding.checkbox1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"체크1번 선택 되었습니다",Toast.LENGTH_SHORT).show()
        }
        activityTestBinding.checkbox2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"체크2번 선택 되었습니다",Toast.LENGTH_SHORT).show()
        }

        //라디오
        activityTestBinding.radio1.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"남자 클릭됨",Toast.LENGTH_SHORT).show()
        }
        activityTestBinding.radio2.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@TestActivity,"여자 클릭됨",Toast.LENGTH_SHORT).show()
        }


} }