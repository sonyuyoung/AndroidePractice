package com.example.myapp_test6_syytest.ch18_Test.retrofit

import com.example.myapp_test6_syytest.ch18_Test.model.UserListModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


/*통신 라이브러리 : retrofit2 이용해서 ,
인터페이스 , 추상메서드를 만들어서 ,
레트로핏한테 전달 : 인터페이스 통으로 전달하면
여기에 정의된함수를 이용해서 통신을함 crud
@GET, @POST , @PUT , @DELETE , @HEAD*/
interface INetworkservice {

    @GET("api/users")
            /*baseurl : http://reqres.in/ 정의할거임
             주소가 정확히 https://reqres.in/api/users?page=2 이건데
             ?page=2 속성을 가져올라면 .
             @Query
             함수 호출할때 -> doGetUserList(3)=> https://reqres.in/api/users?page=3
             반환타입 Call , 담겨진 데이터는 UserModel 리스트의 요소
               */
    /*반환타입 Call import retrofit2 으로*/
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>

    /*프로필 이미지 받기위함 추상함수
    * @Url 기본 baseUrl이 있지만 다른 url을 호출을 할때 사용한다  */
    @GET
    fun getAvatarImage (@Url url:String):Call<ResponseBody>


}