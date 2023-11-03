package com.example.myapp_test6_syytest.ch16_Test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test6_syytest.ch16_Test.adapter.MultiImageAdapter
import com.example.myapp_test6_syytest.databinding.ActivityMultiImageRecyclerBinding

class MultiImageRecyclerActivity : AppCompatActivity() {
    // 전역
    lateinit var binding: ActivityMultiImageRecyclerBinding

    //사진의 위치 Uri 를 가지는 리스트를 하나만들기 : 저장되는경로
    var list = ArrayList<Uri>()
    var adapter = MultiImageAdapter(list, this)
    // 자기자신 이라서 this

    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiImageRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            list.clear()
            // 사진여러장을 선택시 사용할 키워서 ㅣ 사진이 선택이 되었으면
            if (it.data?.clipData != null) {
                //10개까지 제한하는 로직스
                var count = it.data!!.clipData?.itemCount
                if (count != null) {
                    //10개까지 가능!
                    if (count > 10) {
                        Toast.makeText(
                            this@MultiImageRecyclerActivity,
                            "사진은 10장까지만",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
                // 반복문으로 사진데이터의 uri조회해서 List에 담아보자
                for (i in 0 until count!!) {
                    // 여러사진이 이터러블 , 반복이 가능한형태고 인덱스가 있다는 뜻 : 사진의 위치를 가지고오는것임
                    // 각요소의 사진uri가져오기

                    val imageUri = it.data!!.clipData?.getItemAt(i)?.uri
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
                // 반복문으로 , 사진 데이터의 Uri 조회해서 -> 리스트에 담기

            } else {
                it.data.let { uri ->
                    val imageUri: Uri? = it.data?.data
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
                // 여기까지
            }
            adapter.notifyDataSetChanged()
        }

        //1) 버튼 클릭시 갤러리앱 호출
        binding.multiGallaryBtn.setOnClickListener {
            //고정
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            // 추가1) 사진을 여러장 고르는 속성이 추가가된다. 시스템에게 사진여러장 선택 한다는 신고.
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.setType("image/*")
            // 사진에 접근하겠다는 action 문자열 추가2)
            intent.action = Intent.ACTION_GET_CONTENT
            // 후처리
            requestGalleryLauncher.launch(intent)
        }


        // 카메라 호출해서 , 사진 촬영된 사진 가져오기
        //1) 카메라 호출하는버튼, 액션 문자열로 카메라 외부앱 연동
        //2) 후처리 함수를 이용해서 , 촬영된 사진을 결과뷰에 출력하는 로직
        binding.multiGallaryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            // 사진을 여러장 고르는 속성 추가. 시스템에게 사진 여러장 선택 한다는 신고.
            // 추가1
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.setType("image/*")

            // 사진에 접근 하겠다는 정해진 액션 문자열
            // 추가2
            intent.action = Intent.ACTION_GET_CONTENT
            requestGalleryLauncher.launch(intent)

        }

        // 리사이클러뷰 준비 재료 1) 어댑터 2) 뷰홀더 3) 목록 요소의 레이아웃 구성.
        val layoutManager = LinearLayoutManager(this)
        binding.multiImagePickRecycler.layoutManager = layoutManager
        binding.multiImagePickRecycler.adapter = adapter
    }
    // onCreate 끝
}