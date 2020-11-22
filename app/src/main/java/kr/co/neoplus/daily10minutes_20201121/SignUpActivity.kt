package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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