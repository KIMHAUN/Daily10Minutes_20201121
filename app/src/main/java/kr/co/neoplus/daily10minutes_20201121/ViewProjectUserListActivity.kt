package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.neoplus.daily10minutes_20201121.datas.Project
import kr.co.neoplus.daily10minutes_20201121.datas.User
import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil
import org.json.JSONObject

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
        ServerUtil.getProjectUserList(mContext, mProject.id, object:ServerUtil.JsonResponseHandler{
//            갔다와서 뭐할건가요??
            override fun onResponse(json: JSONObject) {
                val dataObj= json.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")

//                 project{} 안에 있는 onging_users[]를 가져다가 분석하자.
                val onGoingUsersArr = projectObj.getJSONArray("ongoing_users")

//              for문 이용 0 ~ 들어 있는 사용자 수 직전까지 돌자.
                for(i in 0 until onGoingUsersArr.length()){
//                    ongoingUsersArr에서 순서에 맞게 {} 하나 추출 => User형태로 변환 => mUserLIst에 추가
                    val userObj = onGoingUsersArr.getJSONObject(i)
                    val user = User.getUserFromJSON(userObj)
                    mUserList.add(user)
                }
    
//              모든 사용자가 mUserList에 추가 되었다.
//              리스트뷰 내용물을 변경시키는 경우일 수도 있다.(연결 먼저, 서버 나중)
//                내용 반영(notifyDataSet..) 처리 필요
            }
        })
    }
}