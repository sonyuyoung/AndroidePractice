package com.example.myapp_test6_syytest.ch11_12_Test.viewPageandrecyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.ch11_12_Test.viewPageandrecyclerview.adapter.RecyclerViewTest
import com.example.myapp_test6_syytest.ch11_12_Test.viewPageandrecyclerview.adapter.ViewPagerAdapterTest
import com.example.myapp_test6_syytest.databinding.ActivityTestPageRecyclerBinding

class TestPageRecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestPageRecyclerBinding

    //    lateinit var binding2 : ActivityTest11ToolBarBinding
    //액션버트느 토글(스위치) 서랍화며난오게하는버튼 전역으로 설정
    lateinit var toggle: ActionBarDrawerToggle

    var newDataNumber = 11

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPageRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //뷰페이저와 리사이클러뷰 2개를붙일예정
        //준비물 : 프레그먼트 형식 어댑터 , 조각인 목록요소격 프래그먼트3가지
        //더미데이터
        binding.viewPager1.adapter = ViewPagerAdapterTest(this)


        // 1. 툴바 붙이기
        setSupportActionBar(binding.toolbar)
        //시스템에 있는 액션바에 업버튼 붙이기
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        2)드로워 화면은 뷰에서 설정함
//        3)네비세이션 뷰추가 설정 -> 뷰에서 작업 재료 1)네비게이션 세더 2) 본문 ) res -> 메뉴
        //뷰1번 네비게이션 헤더 본문  ㅣ res : 메뉴

//        setSupportActionBar(R.id.maintoolbar)


        // 이벤트 핸들러 main_drawer_view it -> 아이템요소 ( 로그인, 로그아웃 , 메인가기등)
        binding.mainDrawerView.setNavigationItemSelectedListener { it ->
            if (it.title == "로그인") {
                Toast.makeText(this@TestPageRecyclerActivity, "로그인 화면 이동", Toast.LENGTH_SHORT)
                    .show()
            } else if (it.title == "로그아웃") {
                Toast.makeText(this@TestPageRecyclerActivity, "로그아웃 화면 이동", Toast.LENGTH_SHORT)
                    .show()
            } else if (it.title == "메인가기") {
                Toast.makeText(this@TestPageRecyclerActivity, "메인가기 화면 이동", Toast.LENGTH_SHORT)
                    .show()
            }
            true
        }

        // 드로워 화면에 액션 버튼 클릭시 -> 드로워 화면에 나오게.
        toggle = ActionBarDrawerToggle(
            this@TestPageRecyclerActivity,
            binding.drawer, R.string.open, R.string.close
        )
        // 실제 화면적용 버튼클릭시 동기화를 거는것 드러워화면열어주는기능
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.floatingActionButton.setOnClickListener {
            when(binding.floatingActionButton.isExtended){
                true->binding.floatingActionButton.shrink()
                false ->binding.floatingActionButton.extend()
            }
            Toast.makeText(this@TestPageRecyclerActivity,"floatingActionButton 클릭됨", Toast.LENGTH_SHORT).show()
            true

        }

        //리사이클러뷰 붙이기
        //준비물 : 1)리사이클러뷰 어댑터 2)목록요소의 아이템뷰 3)더미데이터
        val datas = mutableListOf<String>()
        for (i in 1..10)
            datas.add("더미데이터 추가번호$i")

        //출력 수직
        val layoutManager = LinearLayoutManager(this)


        //리사이클러 뷰 속성 옵션에 출력옵션 붙이기 ! //)
        binding.recyclerViewTest.layoutManager = layoutManager
        binding.recyclerViewTest.adapter = RecyclerViewTest(datas)


        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerViewTest.adapter = customAdapter // 어댑터 설정

//        binding.recyclerViewTest2.layoutManager=layoutManager
        binding.recyclerViewTest2.adapter = RecyclerViewTest(datas)
        binding.recyclerViewTest2.adapter = customAdapter // 어댑터 설정

        binding.addBtn.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

        binding.addBtn2.setOnClickListener {
            datas.removeAt(datas.size - 1)
            customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
        }


//온크리에이크
    }


    //툴바의 오버플로우 메뉴추가 . 이벤트 추가
// 오버플로우 메뉴 이벤트 핸들러 추가하기.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        } else if ( R.id.menu_toolbar1 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar2 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar3 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
            return super.onOptionsItemSelected(item)
    }
    // 람다식에서 return 사용 못함.
    override fun onSupportNavigateUp(): Boolean {
        Log.d("syy", "test")
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("syy", "텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(
                    this@TestPageRecyclerActivity,
                    "검색어가 전송됨 : ${query}",
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}
