package kr.co.neoplus.daily10minutes_20201121

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.neoplus.daily10minutes_20201121.utils.ContextUtil

import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setValues()
        setupEvents()
    }
    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        signInBtn.setOnClickListener {
//            1. 입력된 아이디 / 비번 받아오기
            val inputEmail = emailEdt.text.toString()
            val inputPwd = pwdEdt.text.toString()

//            2. 서버에 맞는 회원인지 확인(로그인 API 호출) => 서버 응답 분석=> UI 반영
            ServerUtil.postRequestLogin(mContext, inputEmail, inputPwd, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    로그인 API를 다녀오면 실행할 내용
//                    다녀 온다 : 서버가 Response를 준다.
//                    그 응답 내용 : json 변수에 담겨 있다.

                    Log.d("화면 : 서버 다녀옴", json.toString())

                    //서버가 알려주는code값 추출해서 LOG로 출력
                    val codeNum = json.getInt("code")


                    Log.d("코드값", codeNum.toString())

//                    받아낸 코드값으로 로그인 성공/실패를 토스트로 출력
                    if (codeNum == 200) {
//                        login success
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()

//                            서버가 알려주는 토큰 추출 json{} => data{} => token String 추출
                            val dataObj = json.getJSONObject("data")
                            val token = dataObj.getString("token")

//                            받은 토큰 값을 저장. (다른 화면에서도 활용) 나의 토큰 : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6ODAsImVtYWlsIjoid2hhMDIwNjhAZ21haWwuY29tIiwicGFzc3dvcmQiOiIwYWQ4NmMwZDA1NDA0YzQwZGY2MGU4MzlhYTRjMjdlMiJ9.yRk9TXyHjSJdpXyD2YooeyBTTt0UZKZSALzWtpUnitYW4qz2enTb3kJJrhvM3KExrxtZUv_RmbZX3p_nov_HvQ
                            ContextUtil.setLoginUserToken(mContext, token)

//                            메인화면으로 진입 + 로그인 화면 종료
                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)

                            finish()

                        }

                    }else{
//                        login fail, 실패 사유
//                        응답에 "message"라는 이름표가 붙은 String을 받아서 사용.
                        val message = json.getString("message")
                        runOnUiThread{
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun setValues() {
    }
}