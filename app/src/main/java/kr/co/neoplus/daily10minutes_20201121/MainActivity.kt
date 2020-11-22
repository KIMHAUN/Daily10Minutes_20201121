package kr.co.neoplus.daily10minutes_20201121

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.neoplus.daily10minutes_20201121.utils.ContextUtil

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        logoutBtn.setOnClickListener {
//            로그인 : 서버에 아이디 비번 맞는지 물어보는 API통신 맞으면 토큰을 기기에 저장.
//            로그아웃 : 저장된 토큰 날려주는 행위 => 저장된 토큰을 ""로 변경
            val alert = AlertDialog.Builder(mContext)
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
//                저장된 토큰 날려주기
                ContextUtil.setLoginUserToken(mContext, "")

//                메인 화면 종료하고 Splash화면으로 넘어가기
                val myIntent= Intent(mContext, SplashActivity::class.java)
                startActivity(myIntent)
                finish()
            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }
    }

    override fun setValues() {
    }


}