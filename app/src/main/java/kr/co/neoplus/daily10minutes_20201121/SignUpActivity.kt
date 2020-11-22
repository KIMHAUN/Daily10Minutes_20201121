package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
//        이메일 입력칸 내용이 변경될 때

        idEdt.addTextChangedListener {
//            한 글자라도 내용이 변경되면 실행되는 부분.
            Log.d("아이디 입력값", idEdt.text.toString())

//            내용이 변경되면 검사 결과 상태 원상태로 복귀
            checkResultTxt.text = "중복 확인을 해주세요."
        }

        //TextWatcher interface 작성 3개를 다 구분해서 쓰는 경우는 거의 없음.
//        idEdt.addTextChangedListener(object : TextWatcher {
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                Log.d("변경된 문구", s!!.toString())
//            }
//
//        })

        emailCheckBtn.setOnClickListener {
//            1. 입력한 이메일 값 확인
            val inputEmail = idEdt.text.toString()

//            2. 서버에 중복인지 물어봄 => API 호출 (서버문서 확인)
            //ServerUtil.getRequestEmailCheck(mContext, inputEmail, null)
            ServerUtil.getRequestEmailCheck(mContext, inputEmail, object:ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
//                  3. 검사 결과를 텍스트뷰에 반영
                    //val code = json.getInt("code")
//                  서버가 내려준 검사 결과 메세지를 텍스트뷰에 반영
                    val message = json.getString("message")
                    runOnUiThread {
                        checkResultTxt.text = message //ui를 건드린다
                    }
                }
            })
        }

        okBtn.setOnClickListener {
//            1.입력한 아이디/비번/닉네임 파악
            val inputId = idEdt.text.toString()
            val inputPwd = pwEdt.text.toString()
            val inputNickName = nickNameEdt.text.toString()

//            2. ServerUtil활용 => 회원가입 API 호출
            ServerUtil.putRequestSignUp(
                mContext,
                inputId,
                inputPwd,
                inputNickName,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
//            3. 돌아와서 어떡할지 ?
                        val code = json.getInt("code")
                        if (code == 200) {
//                        가입 성공 : 가입한 사람의 닉네임을 가지고 환영 토스트
//                        nick님 환영합니다! 등
//                        로그인 화면으로 복귀

                            val dataObj = json.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val nickName = userObj.getString("nick_name")

                            runOnUiThread {
                                Toast.makeText(mContext, "${nickName}님, 환영합니다!", Toast.LENGTH_SHORT).show()
                                finish()
                            }

                        } else {
//                        가입 실패 : message 적힌 가입 실패 사유 받아서 출력
                            val message = json.getString("message")
                            runOnUiThread {
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