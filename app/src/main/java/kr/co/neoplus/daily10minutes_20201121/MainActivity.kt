package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()
    }
    override fun setupEvents() {

        signInBtn.setOnClickListener {
//            1. 입력된 아이디 / 비번 받아오기
            val inputEmail = emailEdt.text.toString()
            val inputPwd = pwdEdt.text.toString()

//            2. 서버에 맞는 회원인지 확인(로그인 API 호출) => 서버 응답 분석=> UI 반영
            ServerUtil.postRequestLogin(inputEmail, inputPwd)





        }
    }

    override fun setValues() {
    }


}