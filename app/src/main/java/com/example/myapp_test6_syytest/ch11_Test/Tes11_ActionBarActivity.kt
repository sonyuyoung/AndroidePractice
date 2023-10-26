package com.example.myapp_test6_syytest.ch11_Test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.databinding.ActivityTes11ActionBarBinding

class Tes11_ActionBarActivity : AppCompatActivity() {
    lateinit var binding: ActivityTes11ActionBarBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 객체를 만들어주는것 . . .
        binding = ActivityTes11ActionBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //시스템에 있는 액션바에 업버튼 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    // 액션바 클릭 이벤트 처리.
    override fun onSupportNavigateUp(): Boolean {
        Log.d("syy","test")
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_main_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("syy","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@Tes11_ActionBarActivity,"검색어가 전송됨 : ${query}",Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    // 오버플로우 메뉴 이벤트 핸들러 추가하기.
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.menu_main1 -> {
            Toast.makeText(this@Tes11_ActionBarActivity,"메뉴1 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_main2 -> {
            Toast.makeText(this@Tes11_ActionBarActivity,"메뉴2 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_main3 -> {
            Toast.makeText(this@Tes11_ActionBarActivity,"메뉴3 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_main4 -> {
            Toast.makeText(this@Tes11_ActionBarActivity,"메뉴4 클릭됨",Toast.LENGTH_SHORT).show()
            true
        }
        // 람다식에서 return 사용 못함.
        else -> super.onOptionsItemSelected(item)
    }

}