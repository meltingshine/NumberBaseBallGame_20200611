package com.example.numberbaseballgame_20200611

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.Toast
import com.example.numberbaseballgame_20200611.adapters.ChatAdapter
import com.example.numberbaseballgame_20200611.datas.Chat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

//     몇번 시도했는지 저장할 변수
    var inputCount = 0

    // 컴퓨터가 낸 문제 숫자 3개를 저장할 ArrayList
    val computerNumbers = ArrayList<Int>() //배열 선언하는법..지금배ㅔ움...ㅅ발..
    val chatMessageList = ArrayList<Chat>() // 채팅 담는 배열

    lateinit var mChatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()

    }

    override fun setupEvents() {
        okBtn.setOnClickListener {
//사용자가 입력한 값을 String으로 우선 저장
            val inputNumStr = numberInputEdt.text.toString()

            if(inputNumStr.length != 3) {
                Toast.makeText(mContext,"숫자는 3자리여야되",Toast.LENGTH_SHORT).show()
                //@으로 명확히 해줘야됨 정확히 return어디껀지 잡아줘야됨.

                return@setOnClickListener
            }

            if(inputNumStr.contains("0")){

                Toast.makeText(mContext,"0은 문제에 포함안돼 멍청아",Toast.LENGTH_SHORT).show()
                //@으로 명확히 해줘야됨 정확히 return어디껀지 잡아줘야됨.

                return@setOnClickListener
            }

            val userChat = Chat("USER", inputNumStr)

//            만든 채팅 메세지를 채팅 내역 배열에 추가
            chatMessageList.add(userChat)
//            리스트뷰의 연결된 배열의 내용이 변하면 반드시 새로고침을 해주자
            mChatAdapter.notifyDataSetChanged()

//            입력하고나면 edittext의 내용을 다시 빈칸으로
//            EditText에는 text=string이 안먹어서 setText로 사용

            numberInputEdt.setText("")

//            썼던 번호를 String이어서 Int로 바꿔서 넣어줌
            checkStrikeandBall(inputNumStr.toInt())

        }

    }

    override fun setValues() {
//    컴퓨터에게 문제를 내라고 시킴 => 문제는 3자리 숫자배열
        makeComputerNumber()
        mChatAdapter = ChatAdapter(mContext, R.layout.chat_iist_item, chatMessageList)
        chatListView.adapter = mChatAdapter
    }


    fun makeComputerNumber() {
// 숫자 3개를 랜덤 생성 => 3번 반복

        for (i in 0..2) {

            //규칙에 맞는 숫자를 뽑을때까지 무한 반복
            while (true) {


                //랜덤은 0<= x <1
                //한다음에 .toInt() 하면 소수점 밑에 버려벌임
                val randomNum = (Math.random() * 9 + 1).toInt()

                Log.d("랜덤값", randomNum.toString())


                // 뽑은 숫자를 사용해도 될지를 저장하는 변수
                var isNumberOk = true

                //중복이 아니어야 사용해도 좋은 숫자로 인정. (1-9는 위에서 셋함)
                //중복 검사 : 문제 숫자 배열에 있는 값들을 다 꺼내서 지금 만든 값과 비교해봄

                for (computerHadNumber in computerNumbers) { //배열을 돌아다니는 문법임

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
            Log.d("최종선별문제", num.toString())
        }

//        문제를 다 내고 안내 메세지를 채팅으로 출력
        chatMessageList.add(Chat("CPU", "숫자 야구 게임에 오신 거슬 환영합니다"))
        chatMessageList.add(Chat("CPU", "3자리 숫자 맞추기"))
        chatMessageList.add(Chat("CPU", "중복 없음, 1-9"))
    }

    //    ?S ?B인지 계산해서 리스트뷰에 답장 띄우기 기능 담당 함수
    fun checkStrikeandBall(inputNum: Int) {

        inputCount++

//    inputNum에는 세자리 숫자가 들어온다고 전제
//    3자리 숫자를 3칸의 배열로 분리
        val inputNumArray = ArrayList<Int>()

//        100의자리, 10의자리, 1의자리 순서대로 대입
        inputNumArray.add(inputNum / 100)
        inputNumArray.add(inputNum / 10 % 10)
        inputNumArray.add(inputNum % 10)
        // %는 나머지 구하는 거

        var strikeCount = 0
        var ballCount = 0

        for (i in inputNumArray.indices) {
            for (j in computerNumbers.indices) {
                if (inputNumArray[i] == computerNumbers[j]) {
                    if (i == j) {
                        strikeCount++
                    } else {
                        ballCount++
                    }
                }
            }
        }
//        ?S ?B 인지 계산 끈났으니 채팅메세지로 보여줘야됨
        val answer = Chat("CPU", "${strikeCount}S ${ballCount}B 입니다")
        chatMessageList.add(answer)
        mChatAdapter.notifyDataSetChanged()

//        리스트뷰에 내용물이 추가되고나서 바닥으로 끌어내기

        chatListView.smoothScrollToPosition(chatMessageList.size-1)

        if (strikeCount == 3) finishGame()

    }

    fun finishGame() {

//    몇번만에 맞혔는지
//    끝났음녀 더이상 입력하지 못하도록함
        numberInputEdt.isEnabled = false
        numberInputEdt.visibility = View.GONE
        okBtn.isEnabled = false
        okBtn.visibility = View.GONE
        val congMessage = Chat("CPU", "축하합니다 정답입니다")
        chatMessageList.add(congMessage)
        chatMessageList.add(Chat("CPU","${inputCount}번 만에 맞췄습니다!"))
        mChatAdapter.notifyDataSetChanged()
        Toast.makeText(mContext, "게임 끝!", Toast.LENGTH_LONG).show()

//    종료 알림 토스트
    }

}

