package com.example.numberbaseballgame_20200611

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.numberbaseballgame_20200611.datas.Chat

class MainActivity : BaseActivity() {
    // 컴퓨터가 낸 문제 숫자 3개를 저장할 ArrayList
    val computerNumbers = ArrayList<Int>() //배열 선언하는법..지금배ㅔ움...ㅅ발..
    val chatMessageList = ArrayList<Chat>() // 채팅 담는 배열


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()

    }

    override fun setupEvents() {
    }

    override fun setValues() {
//    컴퓨터에게 문제를 내라고 시킴 => 문제는 3자리 숫자배열
        makeComputerNumber()
    }


    fun makeComputerNumber() {
// 숫자 3개를 랜덤 생성 => 3번 반복

        for (i in 0..2) {

            //규칙에 맞는 숫자를 뽑을때까지 무한 반복
            while (true) {


                //랜덤은 0<= x <1
                //한다음에 .toInt() 하면 소수점 밑에 버려벌임
                val randomNum = (Math.random()*9+1).toInt()

                Log.d("랜덤값",randomNum.toString())


                // 뽑은 숫자를 사용해도 될지를 저장하는 변수
                var isNumberOk = true

                //중복이 아니어야 사용해도 좋은 숫자로 인정. (1-9는 위에서 셋함)
                //중복 검사 : 문제 숫자 배열에 있는 값들을 다 꺼내서 지금 만든 값과 비교해봄

                for(computerHadNumber in computerNumbers) { //배열을 돌아다니는 문법임

//                    같으면 중복임 사용하면 안됨
                    if (computerHadNumber == randomNum) {
                        isNumberOk = false
                        break //for문 나가기
                    }

                }

                //조건에 맞는 숫자를 뽑으면 무한 반복 끝내기
                if (isNumberOk) {
                    computerNumbers.add(randomNum) //배열에 값 넣는 문법
                    break
                }
            }

        }


        for (num in computerNumbers) {
            Log.d("최종선별문제",num.toString())
        }

//        문제를 다 내고 안내 메세지를 채팅으로 출력
        chatMessageList.add(Chat("CPU","숫자 야구 게임에 오신 거슬 환영합니다"))
        chatMessageList.add(Chat("CPU","3자리 숫자 맞추기"))
        chatMessageList.add(Chat("CPU","중복 없음, 1-9"))
    }

}

