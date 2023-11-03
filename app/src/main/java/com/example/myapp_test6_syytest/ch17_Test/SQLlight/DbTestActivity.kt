package com.example.myapp_test6_syytest.ch17_Test.SQLlight

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityDbTestBinding

class DbTestActivity : AppCompatActivity() {

    // 전역으로 선언만 했지, 할당을 안했음.
    // 그래서, onCreate 라는 함수에서 , 최초 1회 실행시.
    // 할당을 하는 구조.
    //Context : Context 매개변수로정의
    //해당 액티비티에 줄수있음.DatabaseHelper
    var myDB: DatabaseHelper? = null

    lateinit var binding: ActivityDbTestBinding

    var editTextName: EditText? = null
    var editTextPhone: EditText? = null
    var editTextAddress: EditText? = null
    var editTextID: EditText? = null
    var buttonInsert: Button? = null
    var buttonView: Button? = null
    var buttonUpdate: Button? = null
    var buttonDelete: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// 전역에 선언된 변수들을 할당하는 구조.
        binding = ActivityDbTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_main)
        //DatabaseHelper 클래스 를 사용한다.
        // 객체 생성한다. ->
        myDB = DatabaseHelper(this)

        // 자바 버전에 코드 -> 코틀린 변경.
        // findViewById ->  바인딩 기법으로 사용했음.
        editTextName = binding.editTextName
        editTextPhone = binding.editTextPhone
        editTextAddress = binding.editTextAddress
        editTextID = binding.editTextID
        buttonInsert = binding.buttonInsert
        buttonView = binding.buttonView
        buttonUpdate = binding.buttonUpdate
        /*buttonDelete = findViewById(R.id.buttonDelete)*/
        buttonDelete = binding.buttonDelete
        // 최초 1회 실행시, 직접 만든 함수를 호출하는 부분.

        //최초1회실행시 , 직접만들함수를 호출하는부분
        // 다른화면이동이 없어서
        AddData()
        viewAll()
        UpdateData()
        DeleteData()
    }

    //데이터베이스 추가하기
    fun AddData() {
        buttonInsert!!.setOnClickListener {
            val isInserted = myDB!!.insertData(
                editTextName!!.text.toString(),
                editTextPhone!!.text.toString(),
                editTextAddress!!.text.toString()
            )
            if (isInserted == true)
                Toast.makeText(this@DbTestActivity, "데이터추가 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@DbTestActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()
        }
    }

    // 데이터베이스 읽어오기
    fun viewAll() {
        //SAM기법 (single abstrac method) 함수형인터페이스 추상메서드가 하나인 메서드
        //람다식으로 표현할때  .. 자주사용됨 (View.OnClickListener
        buttonView!!.setOnClickListener(View.OnClickListener {
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            // res -> C
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }
            //결과가 있다면.
            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성안함.

            //자원소모가 적고... 문자열인데...
            val buffer = StringBuffer()
            //res 형 ->Cursor , 쉽게 엑셀 마치 테이블 , 0행부터 시작한다.
            // res.moveToNext() -> 1행을 의미.
            // res: 해당 전체 데이터를 여기에 답ㅁ았었음. 결과 Cursor:테이블이라고 생각하기
            while (res.moveToNext()) {
                buffer.append(
                    //코틀린 3중 따옴표, 멀티 라인.
                    // 1행의 첫번째 컬럼을 가져오기.
                    """
    ID: ${res.getString(0)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    이름: ${res.getString(1)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    전화번호: ${res.getString(2)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    주소: ${res.getString(3)}
    
    
    """.trimIndent()
                )
            }
            ShowMessage("데이터", buffer.toString())
        })
    }

    //데이터베이스 수정하기
    fun UpdateData() {
        buttonUpdate!!.setOnClickListener {
            val isUpdated = myDB!!.updateData(
                editTextID!!.text.toString(),
                editTextName!!.text.toString(),
                editTextPhone!!.text.toString(),
                editTextAddress!!.text.toString()
            )
            if (isUpdated == true)
                Toast.makeText(this@DbTestActivity, "데이터 수정 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@DbTestActivity, "데이터 수정 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    // 데이터베이스 삭제하기
    fun DeleteData() {
        buttonDelete!!.setOnClickListener {
            val deleteRows = myDB!!.deleteData(editTextID!!.text.toString())
            if (deleteRows > 0)
                Toast.makeText(this@DbTestActivity, "데이터 삭제 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@DbTestActivity, "데이터 삭제 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    //사용자 정의 다이얼로그창을 자주이용할때 사용하는기본 샘플코드

    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
}