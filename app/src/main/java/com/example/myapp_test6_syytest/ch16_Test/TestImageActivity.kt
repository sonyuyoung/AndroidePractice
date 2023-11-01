package com.example.myapp_test6_syytest.ch16_Test

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.databinding.ActivityTestImageBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class TestImageActivity : AppCompatActivity() {
    // 갤러리,카메라앱 연동해서 데이터 가져오기
    lateinit var binding: ActivityTestImageBinding
    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 작업구성 2가지 .
        // 첫번째 : 해당 갤러리 호출
        // 두번째 : 갤러리앱에서 데이터를 가져온 내용을 처리하는작업

        // 1) 버튼 클릭시 갤러리앱 호출 2) 갤러리에서 선택된사진을 출력하는 뷰 생성


        // 두번째 : 갤러리앱에서 데이터를 가져온 내용을 처리하는작업
        val requestGalleryLauncher = registerForActivityResult(
            //지금 정의하는부분은 시스템에서 각 사용처마다 정의가 다되어있고,
            // 골라서 사용할 예정임 현재는 외부앱 에서 데이터를 가져오는 역할부분을 이용할 예정임
            //StartActivityForResult 각각 정의가 되어있음
            ActivityResultContracts.StartActivityForResult()
        ) {
            // it 여기에 갤러리에서 선택된 사진의데이터가 들어있음 , 위치도 함께
            //불러온 사진을 크기조절 , 바이트로 읽고 비트맵 타입으로 변환하는 작업
            //try catch IO(input/output) 예외처리 필요
            try {
                // calculateInSampleSize 재료
                // 1) fileUri :Uri
                // 2) reqWidth : Int
                // 2) reqHeigh : Int
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    // 가로 , 세로 : 150dp x 150dp
                    resources.getDimensionPixelSize(R.dimen.profile_img_with),
                    resources.getDimensionPixelSize(R.dimen.profile_img_height),
                )
                var options = BitmapFactory.Options()
                // 사진의 크기가, 원하는 크기에 거의 비슷하게 맞게 크기 조절이 됨.
                options.inSampleSize = calRatio

                // 이미지 로딩
                var inputStream = contentResolver.openInputStream(it!!.data!!.data!!)
                // 비트맵 타입으로 변환,-> 옵션에 크기 비율들어 있어서,
                // 비트맵 크기가 거의 150*150에 가까이 되어있음
                val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                inputStream!!.close()
                inputStream = null

                binding.resultUserImage.setImageBitmap(bitmap)
                Log.d("syy","갤러리에서 선택된 사진의 크기 비율 calRatio : $calRatio")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("syy", "사진 출력실패")

            }


        }
        //1) 버튼 클릭시 갤러리앱 호출
        binding.galleryBtn.setOnClickListener {
            // Intent.ACTION_PICK : 갤러리 앱 호출
            //  MediaStore.Images.Media.EXTERNAL_CONTENT_URI : 외부저장소의 사진의 위치
            val intent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //데이터 타입
            intent.type = "image/*"
            //후처리 함수호출 => 데이처를 가져오기위한 후처리함수를 정의하기.
            requestGalleryLauncher.launch(intent)


        }

        var requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // 원본사진 크기 조절하는 비율단위(정수값 )
            // 사진을  디바이스에 넣었을때 어느정도 비율이 줄어들었음
            val calRatio = calculateInSampleSize(Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.profile_img_with),
                resources.getDimensionPixelSize(R.dimen.profile_img_height),)
            val options = BitmapFactory.Options()
            options.inSampleSize = calRatio
            // 촬영된 사진을 bitmap 타입으로 변환.
            val bitmap = BitmapFactory.decodeFile(filePath,options)
            // 비트맵 타입으로 변환된 사진을 출력하기 결과 뷰에
            binding.resultUserImage.setImageBitmap(bitmap)


        }
        // 카메라 호출해서 , 사진 촬영된 사진 가져오기
        //1) 카메라 호출하는버튼, 액션 문자열로 카메라 외부앱 연동
        //2) 후처리 함수를 이용해서 , 촬영된 사진을 결과뷰에 출력하는 로직
        binding.cameraBtn.setOnClickListener {
            //사진이 촬이 되고 저장이된다. 저장이될때 파일이름 정하기
            //중복이 안되게끔 이름을 지어주고
            // 파일이름 준비
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // 촬영된 사진의 저장소 위치 정하기Environment.DIRECTORY_PICTURES : 정해진 위치,갤러리
            var storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            //위에서 만든 파일이름과 저장소위치에 실제 물리파일 생성하기
            val file = File.createTempFile(
                "JPEG_${timeStamp}_", ".jpg", storageDir
            )
            //실제 사진파일 저장소 위치정의 절대경로 전역으로 뺏고 위애서 선언만하고 실제 파일위치가 나올ㄷ이때 할당하는구조
            filePath = file.absolutePath
            Log.d("syy","file.absolutePath:$filePath")

            // 외부앱에서 가져오려면 콘텐츠 프로바이더를 이용해서 가져와ㅇ함
            val photoURI : Uri = FileProvider.getUriForFile(
                this@TestImageActivity,
                "com.example.myapp_test6_syytest.fileprovider",
                file
            )
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 인텐트 데이터를 담아서 전달 키 MediaStore.EXTRA_OUTPUT 값 photoURI
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                // 후처리 함수로 촬영된 사진을 처리하는 로직
            // 아직 정해지지 않았음
            requestCameraFileLauncher.launch(intent)
        }

    }
    // onCreate 끝


    // 크기를 조절해주는 ..
    // 로직을 이해후 함수 통째로 사용
    // 매개변수 1) 사진의 위치 , 2) 요구하는 가로 크기 2) 요구하는세로크기
    // 정리 : 원본의 가로세로크기를 적당히 반으로 접어서 원하는가로 세로 크기만큼의 비율로 계산을 해줌
    /// 비율1 원본 , 크기 4 -> 1/4
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        // 안드로이드에서 이미지타입을 비트맵이라고 정하는데 거기에 옵션을 넣는다
        val options = BitmapFactory.Options()
        //사진에 영향을 안주고 옵션을 제공
        options.inJustDecodeBounds = true
        //이미지로딩
        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            // 변환작업
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("syy", "사진크기비율계산실패")
        }
        // 비율계산
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        // 1: 원본사이즈
        var inSampleSize = 1
        //조건문을 이용해서 내가 요구하는 (원본의 크기예 ) 높이보다 크거나 , 내가 요구하는 폭보다 크면
        //reqHeight/reqHeight:  원하는 사이즈
        if (height > reqHeight || width > reqWidth) {
            //반으로 접기
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            // 반으로 줄인사이즈를 inSampleSize 로 나누어서
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                // 비율을 2배씩 증가 , 값이 증가하면 사이즈의 크기는 줄어든다
                inSampleSize *= 2
            }
        }
        return inSampleSize

    }
}