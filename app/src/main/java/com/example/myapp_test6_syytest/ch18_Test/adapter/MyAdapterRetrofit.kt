package com.example.myapp_test6_syytest.ch18_Test.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test6_syytest.ch18_Test.model.UserModel
import com.example.myapp_test6_syytest.ch18_Test.retrofit.MyApplication
import com.example.myapp_test6_syytest.databinding.ItemRetrofitBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//item_Retrofit
//이미지  하나에 오른쪽 세로 방향 텍스트뷰 3개

class MyViewHolderRetrofit(val binding: ItemRetrofitBinding) : RecyclerView.ViewHolder(binding.root)

//매개변수구성 1) context 2) 데이터
//클래스의 주생성자 , 클래스명 뒤에 오는 소괄호안의 내용 안에 constructor를 생략하고 많이씀
//val context: Context, val datas: List<UserModel>? val ,var 지정 클래스 내에 전역으로 사용가능
// 특정함수내부에서 datas 를 쉽게접근및 사용이 가능
class MyAdapterRetrofit(val context: Context, val datas: List<UserModel>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //리사이클러 틀 요소 할떄 변경되는 부분은 목록 요소 뷰홀더 부분
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        return MyViewHolderRetrofit(
            ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun getItemCount(): Int {
        return datas?.size ?: 0
        // ?널허용연산자 ?: 엘비스 연산자
        //해당값이 있으면 그값을 사용하고: datas.size
        //이게 널이면 엘비스 연산자 다음의 기본값을 사용하시오
    }

    // 해당뷰에 데이터를 연동(바인딩) 해주는 작업
    // datas[position]
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //내부에서 뷰 작업 쉽게 하려고 뷰홀더로 형변환, 바인딩 재할당
        var binding = (holder as MyViewHolderRetrofit).binding

        val user = datas?.get(position)
        //리스트 의 각요소마다 하나씩 꺼내가꼬 변수 user 에 넣움  뷰에 데이터 붙일거임
        binding.retrofitEmailView.text = user?.email
        binding.retrofitFirstNameView.text = user?.firstName
        binding.retrofitLastNameView.text = user?.lastName
//여기서부터 방법1
//       val avatarImageCall = ( context.applicationContext as MyApplication ).networkService.getAvatarImage(user.avatar)
        //이미지만 따로 가지고오는 함수
        //네트워크 함수통해서 처리하는 부분 방법1)
        val avatarImageCall = user?.let {
            (context.applicationContext as MyApplication).networkService.getAvatarImage(
                it.avatar
            )
        }
        //실제로 이미지 가져오는 통신의 시작
        avatarImageCall?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())

                        //방법1
                        binding.retrofitProfileImg.setImageBitmap(bitmap)

                    }
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // 이미지를 가져오기 실패시
                call.cancel()
            }

        })
    }
    }
// 목록의 아이템의 요소 뷰를 만들기

//방법2
/*     Glide.with(context)
         //load 실제 URL주소 직접넣기
         .load(user?.avatar)
         .override(100,100)
         .into(binding.retrofitProfileImg)*/