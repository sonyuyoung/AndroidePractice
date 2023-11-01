package com.example.myapp_test6_syytest.ch11_12_Test

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.databinding.ActivityTest11FragmentBinding

class Test11_FragmentActivity : AppCompatActivity() {
//    프레그먼트를 출력하기위한 베이스로 사용할 엑티비티
    lateinit var binding: ActivityTest11FragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test11_fragment)

        binding= ActivityTest11FragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트 출력하기. 연결(바인딩)
        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()


        //  첫번째 프레그먼트 붙이기
        val oneFragment = OneFragment()
        // 두번째 프레그먼트 붙이기
        val twoFragment = TwoFragment()
        //세번째 프레그먼트
        val threeFragment = ThreeFragment()

        val fourFragment = FourFragment()

        transaction.add(R.id.fragment1,oneFragment)
        transaction.add(R.id.fragment2,twoFragment)
        transaction.add(R.id.fragment3,threeFragment)
        transaction.add(R.id.fragment4,fourFragment)
        // 백스택, 화면출력시, task 라는 공간을 ㅏㅅ용해서 ( 메모리 사용 )
        // 화면전환이 발생할경우 매번 프래그먼트 소멸 시키고 또 생성하고 작업이 반복될시
        // 자원이 비효율적이니 , 잠시 keep 하고있다가 다시 그려주기 ( 소멸시키지 않고 )
        // 액티비티에서도 기존의 액티비티를 최대한 활용한ㄴ 방않으로 singletop을 소개시켜주신다함
        // 뷰페이저 등을 이용해서, 프레그먼트 전환할때 다시 재확인할것
        transaction.addToBackStack(null)

        // 화면에 붙이기
        transaction.commit()
        // 툴바 붙이기
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
                Toast.makeText(this@Test11_FragmentActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    // 오버플로우 메뉴 이벤트 핸들러 추가하기.
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.menu_toolbar1 -> {
            Toast.makeText(this@Test11_FragmentActivity,"메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_toolbar2 -> {
            Toast.makeText(this@Test11_FragmentActivity,"메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_toolbar3 -> {
            Toast.makeText(this@Test11_FragmentActivity,"메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.menu_toolbar4 -> {
            Toast.makeText(this@Test11_FragmentActivity,"메뉴4 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        // 람다식에서 return 사용 못함.
        else -> super.onOptionsItemSelected(item)
    }


}
