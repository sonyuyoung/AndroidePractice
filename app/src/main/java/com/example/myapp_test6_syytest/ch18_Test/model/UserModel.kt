package com.example.myapp_test6_syytest.ch18_Test.model

import com.google.gson.annotations.SerializedName


//가져오는 데이터 타입 조사
/*"id": 7,
"email": "michael.lawson@reqres.in",
"first_name": "Michael",
"last_name": "Lawson",
"avatar": "https://reqres.in/img/faces/7-image.jpg"*/
data class UserModel(

    val id:String,

    @SerializedName("first_name")
    // firstName -> first_name으로 저장
    val firstName:String,
    @SerializedName("last_name")
    val lastName:String,
    // 프로필이미지가 저장된 위치의 url 주소
    val avatar : String,

    // 추가로 속성값 더 가져오기 테스트


)
