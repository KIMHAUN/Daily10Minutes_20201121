package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.neoplus.daily10minutes_20201121.datas.Project
import kr.co.neoplus.daily10minutes_20201121.datas.User
import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil

class ViewProjectUserListActivity : BaseActivity() {

    lateinit var mProject : Project

    val mUserList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_user_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mProject = intent.getSerializableExtra("project") as Project

//        서버에서 사용자 목록 가져오는 함수 별도 실행.
        getUserListFromServer()
    }

    fun getUserListFromServer(){
//        서버에 접근해서 사용자 목록 받아오는 작업. => 내용 분석 + mUserList에 담아주기
        //ServerUtil.getProjectUserList()
    }
}