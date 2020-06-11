package com.example.numberbaseballgame_20200611.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.numberbaseballgame_20200611.R
import com.example.numberbaseballgame_20200611.datas.Chat
import kotlinx.android.synthetic.main.activity_main.view.*

class ChatAdapter(val mContext: Context, val resId: Int, val mList: List<Chat>) :
    ArrayAdapter<Chat>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        tempRow?.let {

        }.let {
            tempRow = inf.inflate(R.layout.chat_iist_item, null)
        }
        val row = tempRow!!

//        컴퓨터가 말할때와 사람이 말할때 보여지는 레이아웃이 다르게 하려고함
        val computerChatLayout = row.findViewById<LinearLayout>(R.id.computerChatLayout)
        val userChatLayout = row.findViewById<LinearLayout>(R.id.userChatLayout)

//        뿌려줄 채팅 데이터 확인
        val data = mList[position]

        when (data.who) {
            "USER" -> {
//          사람메시지는 보여주고 컴퓨터 메세지는 숨김
                userChatLayout.visibility = View.VISIBLE
                computerChatLayout.visibility = View.GONE
            }
            "CPU" ->{
                userChatLayout.visibility = View.GONE
                computerChatLayout.visibility = View.VISIBLE

            }
    else -> {
        Log.e("에러발생","사용자도 컴퓨터도 아닌 경우")
    }
        }

        return row

    }
}