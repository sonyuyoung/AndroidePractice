package com.example.myapp_test6_syytest.ch18_Test.retrofit

import com.example.myapp_test6_syytest.ch18_Test.model.newsModel.ItemListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/*통신 라이브러리 : retrofit2 이용해서 ,
인터페이스 , 추상메서드를 만들어서 ,
레트로핏한테 전달 : 인터페이스 통으로 전달하면
여기에 정의된함수를 이용해서 통신을함 crud
@GET, @POST , @PUT , @DELETE , @HEAD*/
interface NetworkService {
/*    https://newsapi.org
     /v2/everything
    파라미터 값 ?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=87af28a1123a4fcc9c869c0b81bd243c */

//    baseurl 주소 기본값에 이어서 뒤에 추가 주소 설정
//    쿼리 설정으로 파라미터값 설정
@GET("/v2/everything")
fun getList(
    //파라미터값 설정
    //1)q 2) from 3) sortBy 4) apiKey
    @Query("q") q: String?,
    @Query("from") from : String,
    @Query("sortBy") sortBy : String,
    @Query("apiKey") apiKey : String,
) : Call<ItemListModel>



}
