package com.example.numberbaseballgame_20200611

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : BaseActivity() {
    // 컴퓨터가 낸 문제 숫자 3개를 저장할 ArrayList
    val computerNumbers = ArrayList<Int>() //배열 선언하는법..지금배ㅔ움...ㅅ발..


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
//                TODO 1에서 9의 숫자를 랜덤으로 뽑음


                // 뽑은 숫자를 사용해도 될지를 저장하는 변수
                var isNumberOk = true

                //TODO 뽑은 숫자를 써도 되는지 검사 로직 => isNumberOk 의 값 변경

                //조건에 맞는 숫자를 뽑으면 무한 반복 끝내기
                if (isNumberOk) break
            }

        }

    }

}
}
