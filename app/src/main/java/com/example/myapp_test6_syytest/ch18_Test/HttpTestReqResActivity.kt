package com.example.myapp_test6_syytest.ch18_Test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6_syytest.ch18_Test.adapter.MyAdapterRetrofit
import com.example.myapp_test6_syytest.ch18_Test.model.UserListModel
import com.example.myapp_test6_syytest.ch18_Test.retrofit.MyApplication
import com.example.myapp_test6_syytest.databinding.ActivityHttpTestReqResBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpTestReqResActivity : AppCompatActivity() {
    lateinit var binding : ActivityHttpTestReqResBinding
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        binding = ActivityHttpTestReqResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // reqres.in : 외국에서 무료 rest테스트 서버 제공 해주는곳
        // 데이터를 받아서 리사이클러뷰 출력
        // 리사이클러뷰 1) 어댑터 2) 휴홀더 3) 목록요소의 뷰 4) 데이터



        // http 통신 준비물
        // 1) Myapplication 설정파일 2) 인터페이스 3) 모델 또는 모델이 담겨진 리스트 필요 .
        // 준비작업 1) 모델준비하기
        // 준비작업 2) 모델을 요소로 하는 리스트로 준비하기
        // 주의사항.https://reqres.in/api/users?page=2

        // 작업순서 2 ) 네트워크 interface 정의하기  INetworkservice interface
        // 작업순서 3 ) MyApplication baseurl 등록 했고, 우리가 만든 인터페이스를 연결했음.
        // 매니페스트에 등록해서 앱 실행 선언및 자동초기화 되어 바로 사용하면됨
        // 작업순서 4 ) retrofit 통신 이용 데이터 전달 유무 확인
        // 작업순서 5 ) 리사이클러뷰에 넣는 작업

//        4
        // applicationContext 안에 우리가 등록한 설정이 있고, as MyApplication 으로 형변환
        // 형변환된 인스턴스 내부에 networkService 를 사용하기
        val networkService = (applicationContext as MyApplication).networkService
        //호출하는 함수콜 만들기 우리가 만든 인터페이스에 등록된 추상함수를 이용하고 인자값은 페이지번호정의를 문자열타입으로 .
        val userListCall = networkService.doGetUserList("2")

        //실제 통신이 시작이되는부분 이고 이걸 통해 데이터를 받아옴.
        //
        userListCall.enqueue(object : Callback<UserListModel>{
            //익명클래스가 콜백(레트로핏2)이 제공하는 인터페이스를 구현햇고 ,
            //반드시 재정의 해야하는 함수들이 있음 .
            //데이터를 성공적으로 받았을때 /데이터 받기에 실패했을때 함수
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                val userList = response.body()
                Log.d("syy","userList의 값 : ${userList?.data}")


                //데이터를 성공적으로 받았다면 , 리사이클러 뷰 어댑터에 연결
                //리사이클러 레이아웃정하는 부분 LinearLayoutManager 이용
                val layoutManager = LinearLayoutManager(this@HttpTestReqResActivity)
                //리사이클러뷰에 어댑터를 연결하고
                binding.retrofitRecyclerView.layoutManager=layoutManager
                //인자값은 : 현재 컨텍스트 2번재 인자값을 ..
                binding.retrofitRecyclerView.adapter =
                    MyAdapterRetrofit(
                    this@HttpTestReqResActivity,userList?.data)

            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
               call.cancel()
            }
        })

    } //onCreate


}