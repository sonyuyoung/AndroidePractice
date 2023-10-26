package com.example.myapp_test6_syytest.ch10_Test

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapp_test6_syytest.R
import com.example.myapp_test6_syytest.databinding.ActivityTest102Binding


class Test10_2Activity : AppCompatActivity() {
    // 추가
    lateinit var activityTest102Binding: ActivityTest102Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 추가
        activityTest102Binding = ActivityTest102Binding.inflate(layoutInflater)
        // 변경
        setContentView(activityTest102Binding.root)

        // 버튼의 요소를 선택해서, 알림을 보내는 로직을 추가할 예정.
        activityTest102Binding.notiBtn.setOnClickListener {
            // 알림 추가 설정.
            // getSystemService(NOTIFICATION_SERVICE) 결과 형이 object 이어서,
            // as NotificationManager -> 형 변환.
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            // sdk 버전에 따라서, 분기, 기능의 패키지명 또는 구현 형식을 달라져서.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //채널 설정
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_HIGH
                )

                // 채널의 정보 및 부가 옵션 설정.
                channel.description = "My Channel One 설명"
                // 알림의 갯수를 아이콘 표시
                channel.setShowBadge(true)
                //채널에 시스템알림을 설정연결
                // 음원, 이미지, uri , 영상등의 위치를 알려주는 타입  예시로 http://도메인주소이고 , contents://경로
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                channel.setSound(uri, audioAttributes)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                // 진동의 간격마다, 세기 주기설정.
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

                // Notification Manager에 채널 등록 하기.
                manager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(this@Test10_2Activity, channelId)
            } else {
                // 26 버전 이하 버전시 사용하는 형식.
                builder = NotificationCompat.Builder(this@Test10_2Activity)
            }

            //아이콘, 테마, 툴바, 액션바 overlay 단어가 보이면 투명지, 뒤에 부분이 보인다.
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("알림 제목")
            builder.setContentText("알림의 메세지 내용")

            // 알림 메세지 창 클릭시, 페이지 이동. 기존에 사용했던 , 인텐트 방식과 비슷.
            val intent = Intent(this@Test10_2Activity, Test10_1Activity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                this@Test10_2Activity, 10, intent,
                FLAG_IMMUTABLE
            )
            // 2번째 액션 인텐트 테스트 하기위해 잠시 주석.
            //builder.setContentIntent(pendingIntent)

            // 특정 액션 추가 기능 넣기. 더미
            // 단순 페이지 이동으로 사용했었지만 다른 추가적인 기능으로도 사용이가능 .

            val actionIntent = Intent(this@Test10_2Activity, OneReceiver::class.java)
            // 현재 엑티비티에서, 요청코드를 20번으로 구분 . 4대 컨포넌트중 하나인 OneReceiver
            // activity -> other -> 브로드 캐스트
            val actionPendingIntent = PendingIntent.getBroadcast(
                this@Test10_2Activity, 20,
                actionIntent, FLAG_IMMUTABLE
            )
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "Action",
                    actionPendingIntent
                ).build()
            )

            // 특정 액션 추가 부분인데, 위에는 기본 액션 1개를 추개했고, 답장이라는 추가 액션 넣기.
            val KEY_TEXT_REPLY = "key_text_reply"
            val replyLabel: String = "답장"
            var remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build() // 이 부분을 누락하지 않도록 수정
            }
            val replyIntent = Intent(this@Test10_2Activity, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(
                this@Test10_2Activity, 30,
                replyIntent, FLAG_MUTABLE
            )
            // 답장 액션 추가하기.
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

            //알림 발생 시키기
            manager.notify(11, builder.build())
            //프로그래스 진행바
//            builder.setProgress(100,0,false)
//            thread {
//                for(i in 1..100){
//                builder.setProgress(100,i,false)
////                    builder.setProgress(100,i,true)
//                manager.notify(11,builder.build())
//                    SystemClock.sleep(100)
//            }
//            }
            // 큰이미지 첨부해서 알림보내기
            val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.matzip)
            val bigStyle = NotificationCompat.BigPictureStyle()
            bigStyle.bigPicture(bigPicture)
            builder.setStyle(bigStyle)

            // 긴텍스트
            val bigTextStyle = NotificationCompat.BigTextStyle()
            bigTextStyle.bigText(resources.getString(R.string.long_text))
            builder.setStyle(bigTextStyle)


            // 박스 스타일 형식
            val boxStyle = NotificationCompat.InboxStyle()
            boxStyle.addLine("1코스 - 짜장면")
            boxStyle.addLine("2코스 - 우동")
            boxStyle.addLine("1코스 - 잡채밥")
            boxStyle.addLine("1코스 - 해물우동")
            builder.setStyle(boxStyle)


            //알림 발생 시키기
            manager.notify(11, builder.build())
        }
    }
}