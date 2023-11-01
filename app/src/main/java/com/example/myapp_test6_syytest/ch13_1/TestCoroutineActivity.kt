package com.example.myapp_test6_syytest.ch13_1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test6_syytest.databinding.ActivityTestCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class TestCoroutineActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testCoBtn.setOnClickListener {
//        Log.d("syy", ":$time")
//        binding.resultCoView.text = "time: $time ,sum +$sum"

            //ANR 오류 해결 하고자 스레드- 핸들러 구조로 다시작성
            //방법 1 ) 비동기 작업 핸들러 방식 deprecated
//            var handler = object : Handler() {
//                override fun handleMessage(msg: Message) {
//                    super.handleMessage(msg)
//                    binding.resultCoView.text = "time: ${msg.arg2} , sum:${msg.arg1}"
//                }
//            }
            //thread 작업 , 비동기 작업 코루틴에 비해서 성능이 조금무거움
//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//
//                    //오래걸리는 작업
//                    var sum = 0L
//                    var time = measureTimeMillis {
//                        for (i in 1..9_000_000_000) {
//                            sum += i
//                        }
//                    }
//                    // 오래걸리는 작업의 결과값을 메세지를통해서 핸들러에게 전달하는로직
//                    var message = Message()
//                    message.arg1 = sum.toInt()
//                    message.arg2 = time.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("syy", "time:$time")
//            }

            //방법2 ) 코루틴 이용해서 비동기 처리해보기
            // 무거운 작업의 결과값을 전달하는도구
            var channel = Channel<Int>()
            // 무거운 작업을 수행하는로직
            var backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000) {
                        sum += i
                    }
                }
                Log.d("syy", "time:$time")
                channel.send(sum.toInt())
//                channel.send(time.toInt())

            }
            var mainScope = GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultCoView.text = "sum:$it"
                }
            }
        }
    }}