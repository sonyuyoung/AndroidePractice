package com.example.myapp_test6_syytest.ch18_Test.model


//data값의 배열에 요소를 모델링 : UserModel
//UserModel 을 요소로 갖는 리스트 만들기- > 모델화

/*
"page": 2,
"per_page": 6,
"total": 12,
"total_pages": 2,
"data": [*/

data class UserListModel(
    val page :String,
    val perPage : String,
    val total :String ,
    val totalPages:String,
    // 이름 data
    val data:List<UserModel>

)
