package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            ServerUtil.putRequestSignUp(mContext, inputId, inputPwd, inputNickName, object: ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
//            3. 돌아와서 어떡할지 ?

                }
            })
        }
    }

    override fun setValues() {
    }
}