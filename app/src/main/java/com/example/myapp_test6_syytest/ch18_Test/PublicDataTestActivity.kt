package com.example.myapp_test6_syytest.ch18_Test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6_syytest.ch18_Test.adapter.MyAdapterRetrofit3
import com.example.myapp_test6_syytest.ch18_Test.model.PublicModel.ItemListModel3
import com.example.myapp_test6_syytest.ch18_Test.retrofit.MyApplication3
import com.example.myapp_test6_syytest.databinding.ActivityPublicDataTestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PublicDataTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityPublicDataTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicDataTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val networkService3 = (applicationContext as MyApplication3).networkService
        val serviceKey3 ="ALRX9GpugtvHxcIO/iPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH/AKv+A1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ=="
        val resultType="json"
//        val userListCall = networkService3.getList2(serviceKey3,1,30,resultType)
        val userListCall = networkService3.getList3(serviceKey3,1,30,resultType)

//변경3
        // 실제 통신이 시작이 되는 부분, 이 함수를 통해서 데이터를 받아옴.
        userListCall.enqueue(object : Callback<ItemListModel3> {
            //익명 클래스가, Callback , 레트로핏2에서 제공하는 인터페이스를 구현했고,
            // 반드시 재정의해야하는 함수들이 있음.
            // 변경4
                override fun onResponse(call: Call<ItemListModel3>, response: Response<ItemListModel3>) {
                    // 데이터를 성공적으로 받았을 때 수행되는 함수
                    val userList = response.body()
                    // 변경8
                    Log.d("syy","userList의 값 : ${userList?.getWalkingKr}")

                // 데이터를 성공적으로 받았다면, 여기서 리사이클러 뷰 어댑터에 연결하면 됨.
                // 리사이클러뷰 의 레이아웃 정하는 부분, 기본인 LinearLayoutManager 이용했고,

                //변경6
                val layoutManager = LinearLayoutManager(
                    this@PublicDataTestActivity)
                // 리사이클러뷰에 어댑터 연결
                // 인자값은 : 현재 context : this@HttpTestReqResActivity
                // 2번째 인자값은 : 데이터 , 네트워크 ,레트로핏2 통신으로 받아온 데이터 리스트

                //변경7
                binding.retrofitRecyclerView3.layoutManager = layoutManager
                // 변경9
                binding.retrofitRecyclerView3.adapter =
                    MyAdapterRetrofit3(this@PublicDataTestActivity,userList?.getWalkingKr?.item)

            }

            //변경5
            override fun onFailure(call: Call<ItemListModel3>, t: Throwable) {

                // 데이터를 못 받았을 때 수행되는 함수
                call.cancel()
            }

        })


    }
}
