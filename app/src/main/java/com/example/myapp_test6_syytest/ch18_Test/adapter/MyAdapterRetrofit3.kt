package com.example.myapp_test6_syytest.ch18_Test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp_test6_syytest.ch18_Test.model.PublicModel.ItemModel3
import com.example.myapp_test6_syytest.databinding.ItemRetrofit2Binding


//item_Retrofit2
//이미지  하나에 오른쪽 세로 방향 텍스트뷰 3개

class MyViewHolderRetrofit3(val binding: ItemRetrofit2Binding) :
    RecyclerView.ViewHolder(binding.root)

//매개변수구성 1) context 2) 데이터
//클래스의 주생성자 , 클래스명 뒤에 오는 소괄호안의 내용 안에 constructor를 생략하고 많이씀
//val context: Context, val datas: List<UserModel>? val ,var 지정 클래스 내에 전역으로 사용가능
// 특정함수내부에서 datas 를 쉽게접근및 사용이 가능
class MyAdapterRetrofit3(val context: Context, val datas: List<ItemModel3>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //리사이클러 틀 요소 할떄 변경되는 부분은 목록 요소 뷰홀더 부분
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        return MyViewHolderRetrofit3(
            ItemRetrofit2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        var binding = (holder as MyViewHolderRetrofit3).binding

        val Item = datas?.get(position)
        //리스트 의 각요소마다 하나씩 꺼내가꼬 변수 user 에 넣움  뷰에 데이터 붙일거임
        binding.retrofitTitleView.text = Item?.TITLE
        binding.retrofitContentView.text = Item?.MAIN_TITLE


// 목록의 아이템의 요소 뷰를 만들기

        Glide.with(context)
            //load 실제 URL주소 직접넣기
            .load(Item?.MAIN_IMG_NORMAL)
            .override(500, 300)
            .into(binding.retrofitProfileImg2)
    }
}