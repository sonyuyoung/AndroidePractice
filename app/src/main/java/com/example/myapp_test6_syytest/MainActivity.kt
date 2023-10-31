package com.example.myapp_test6_syytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.myapp_test6_syytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //시스템에 있는 액션바에 업버튼 붙이기
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Using view binding to handle button click
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginFormActivity::class.java)
            startActivity(intent)
        }

        binding.joinBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, JoinActivity::class.java)
            startActivity(intent)
        }
        // 툴바 붙이기
        setSupportActionBar(binding.toolbar)

        // 이벤트 핸들러 main_drawer_view it -> 아이템요소 ( 로그인, 로그아웃 , 메인가기등)
        binding.mainDrawerView.setNavigationItemSelectedListener { it ->
            if (it.title == "로그인") {
                Toast.makeText(this@MainActivity, "로그인 화면 이동", Toast.LENGTH_SHORT)
                    .show()
            } else if (it.title == "로그아웃") {
                Toast.makeText(this@MainActivity, "로그아웃 화면 이동", Toast.LENGTH_SHORT)
                    .show()
            } else if (it.title == "메인가기") {
                Toast.makeText(this@MainActivity, "메인가기 화면 이동", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }


        // 드로워 화면에 액션 버튼 클릭시 -> 드로워 화면에 나오게.
        toggle = ActionBarDrawerToggle(this@MainActivity,
            binding.drawer, R.string.open, R.string.close)
        // 화면에 붙이는 작업, 적용하기.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 버튼 클릭시, 동기화, 드러워 화면을 열어주는 기능.
        toggle.syncState()

        //4) floating action button
//        // 이벤트 추가하기.
//        binding.floatingActionButton.setOnClickListener {
//            when(binding.floatingActionButton.isExtended) {
//                true -> binding.floatingActionButton.shrink()
//                false -> binding.floatingActionButton.extend()
//            }
//            Toast.makeText(this@MainActivity,"floatingActionButton 클릭됨", Toast.LENGTH_SHORT).show()
//
//    }
    }
    override fun onSupportNavigateUp(): Boolean {
        Log.d("syy","test")
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
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
                Toast.makeText(this@MainActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        } else if ( R.id.menu_toolbar1 == item.itemId) {
            Toast.makeText(this@MainActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar2 == item.itemId) {
            Toast.makeText(this@MainActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar3 == item.itemId) {
            Toast.makeText(this@MainActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        return super.onOptionsItemSelected(item)
    }

}

