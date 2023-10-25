package com.example.myapp_test6_syytest.ch9

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowMetrics
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapp_test6_syytest.LoginFormActivity
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.databinding.ActivityTest9Binding


class Test9Activity : AppCompatActivity() {
    lateinit var activityTest9Activity: ActivityTest9Binding

    @RequiresApi(Build.VERSION_CODES.R)
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
            Log.d("syy", "권한이 승인됨: ${status}")
        } else {
            Toast.makeText(this@Test9Activity, "위치권한 승인안됨", Toast.LENGTH_SHORT).show()
            Log.d("syy", "권한이 승인안됨: ${status}")
        }
        // 후처리 인텐트 기본적인 사용법이나 단순페이지 이동만 사용했음
        // 특정앱에 접근해서 데이터를 가져오는 작업 ( 후처리 )
        // 설정1)
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.d("lsy", "권한이 승인됨 , call back 후처리 요청. ")
            } else {
                Log.d("lsy", "권한이 승인안됨 , call back 후처리 요청. ")
            }
        }
        // 이용 -> 호출, 위에 설정으로
        requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")

        activityTest9Activity.testToastBtn?.setOnClickListener {

            val toast = Toast.makeText(this@Test9Activity, "후처리 확인중", Toast.LENGTH_LONG)
            toast.addCallback(
                @RequiresApi(Build.VERSION_CODES.R)
                object : Toast.Callback() {
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("lsy", "토스트 후처리 작업: 사라질 경우 ")
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("lsy", "토스트 후처리 작업: 나타날 경우 ")
                    }
                }
            )
            toast.show()
        }
        //날짜 다이얼로그 출력해보기
        activityTest9Activity.dateBtn?.setOnClickListener {
            DatePickerDialog(this@Test9Activity, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("lsy", "년도: ${year}년, 월: ${month + 1}월, 일: ${dayOfMonth}일")
                    Toast.makeText(
                        this@Test9Activity,
                        "년도: ${year}년, 월: ${month + 1}월, 일: ${dayOfMonth}",
                        Toast.LENGTH_SHORT
                    ).show()
                    activityTest9Activity.dateTextView?.text =
                        " ${year}년,  ${month + 1}월,  ${dayOfMonth}일"
                } //텍스트 뷰에 설정해보깅
            }, 2023, 9, 25).show()
        }

        activityTest9Activity.timeBtn?.setOnClickListener {
            TimePickerDialog(this@Test9Activity, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Log.d("lsy", "${hourOfDay}시, ${minute}분")
                    Toast.makeText(
                        this@Test9Activity, "${hourOfDay}시, ${minute}분", Toast.LENGTH_SHORT
                    ).show()
                    // 텍스트 뷰에 설정해보기.
                    activityTest9Activity.timeTextView?.text = "${hourOfDay}시, ${minute}분"
                }
            }, 14, 21, true).show()


            //커스터마이징한 다이얼로그 출력해보기
            activityTest9Activity.cutomDialogBtn?.setOnClickListener {
                AlertDialog.Builder(this@Test9Activity).run {
                    setTitle("custom dialog")
                    setIcon(android.R.drawable.ic_dialog_info)
                    setMessage("정말테스트할까여?")
                    setPositiveButton("수락", null)
                    setNegativeButton("취소", null)
                    setNeutralButton("더보기", null)
                    show()


                }
            }

        }
    }
}
//기존 사용법
//            Toast.makeText(this@Test9Activity,"후처리 확인중",Toast.LENGTH_LONG).show()
//콜백을 익명클래스 추가해서 사라지거나 또는 나타나거나 했을경우 추가 로직넣기