package com.example.myapplication_7_8_9_10.ch8_Test

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_7_8_9_10.databinding.ActivityTest8Binding
class Test8Activity : AppCompatActivity() {
    lateinit var activityTest8Activity: ActivityTest8Binding
    lateinit var TAG: String

    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = "syy"
        // setContentView(R.layout.activity_test8)
        // 모든뷰들의 인스턴스들을 하나의 인스턴스에 모두담기
        // 하나의 인스턴스에서 뷰를 꺼내어서 사용하기.
        activityTest8Activity = ActivityTest8Binding.inflate(layoutInflater)
        // 모두담은 인스턴스의 부모 레이아웃을 출력
        setContentView(activityTest8Activity.root)
        // 버튼클릭 리스터 이벤트 처리 sam 기법 (Sing Abc
        // 자바에서 람다식을 연결할때 사용
        //
      activityTest8Activity.testBtn.setOnClickListener {
          Toast.makeText(this@Test8Activity,"test !!!!",Toast.LENGTH_SHORT).show()
      }
        //롱클릭 이벤트 클릭을 했을때 메뉴가 나타나게 하는.. 것도 응용할수있음 .
        activityTest8Activity.longBtn.setOnLongClickListener{
            Toast.makeText(this@Test8Activity,"long Clicked !!!!",Toast.LENGTH_SHORT).show()
            true

        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(
                    TAG, "touch down event x:${event.x},event rawX:${event.rawX}, " +
                            "event y:${event.y},event rawY:${event.rawY}  "
                )
//                Toast.makeText(this@Test8Activity,"touch down event",Toast.LENGTH_SHORT).show()
            }

            MotionEvent.ACTION_UP -> {
                Log.d(
                    TAG, "touch up event x:${event.x},event rawX:${event.rawX}, " +
                            "event y:${event.y},event rawY:${event.rawY}  "
                )
//                Toast.makeText(this@Test8Activity,"touch up event",Toast.LENGTH_SHORT).show()
            }

            MotionEvent.ACTION_MOVE -> {
                Log.d(
                    TAG, "touch move event x:${event.x},event rawX:${event.rawX}, " +
                            "event y:${event.y},event rawY:${event.rawY}  "
                )
//                Toast.makeText(this@Test8Activity,"touch move event",Toast.LENGTH_SHORT).show()

            }
        }
        return super.onTouchEvent(event)


    }

    //==============================================================================================
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_0 -> Log.d(TAG, "0키누름")
            KeyEvent.KEYCODE_A -> Log.d(TAG, "A키누름")
            KeyEvent.KEYCODE_ENTER -> Log.d(TAG, "enter키누름")

        }
        return super.onKeyDown(keyCode, event)
    }
}

