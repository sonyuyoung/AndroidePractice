package com.example.myapp_test6_syytest.ch18_Test.retrofit

import com.example.myapp_test6_syytest.ch18_Test.model.PublicModel.ItemListModel3
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/*통신 라이브러리 : retrofit2 이용해서 ,
인터페이스 , 추상메서드를 만들어서 ,
레트로핏한테 전달 : 인터페이스 통으로 전달하면
여기에 정의된함수를 이용해서 통신을함 crud
@GET, @POST , @PUT , @DELETE , @HEAD*/

/*https://apis.data.go.kr/6260000/
WalkingService/getWalkingKr
?serviceKey=ALRX9GpugtvHxcIO%2FiPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH%2FAKv%2BA1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ%3D%3D&
pageNo=1
&numOfRows=100&
resultType=json*/
interface NetworkService3 {

    @GET("WalkingService/getWalkingKr")
fun getList3(
    //파라미터값 설정
    //1)q 2) from 3) sortBy 4) apiKey
    @Query("serviceKey") serviceKey: String?,
    @Query("pageNo") pageNo: Int,
    @Query("numOfRows") numOfRows: Int,
    @Query("resultType") resultType: String
) : Call<ItemListModel3>



}
