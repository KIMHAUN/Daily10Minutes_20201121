package kr.co.neoplus.daily10minutes_20201121

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.neoplus.daily10minutes_20201121.adapters.ProjectAdapter
import kr.co.neoplus.daily10minutes_20201121.datas.Project
import kr.co.neoplus.daily10minutes_20201121.utils.ContextUtil
import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectArrayList = ArrayList<Project>()
    lateinit var mAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()

        //        액션바 자체를 커스텀으로 그리려면?
       // setCustomActionBar()

    }
    override fun setupEvents() {

        projectListView.setOnItemClickListener { parent, view, position, id ->

            val clickedProject = mProjectArrayList[position]
//            상세 화면으로 이동 => 클릭된 프로젝트를 통째로 넘기자.
            val myIntent = Intent(mContext, ViewProjectDetailActivity::class.java)

            myIntent.putExtra("project", clickedProject)

            startActivity(myIntent)
        }

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

//        액션바의 제목을 바꾸는 제일 기본적인 방법

//        title = "메인화면" //쉬운 대신 자주 사용되지 않는다.

//        BaseActivity가 물려준 backBtn! 숨겨보리기.
        backBtn.visibility = View.GONE

//        서버에 어떤 프로젝트들이 있는지 API 호출 => 그 결과(JSON)을 파싱해서 ArrayList에 대입
        getProjectsFromServer()

        mAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectArrayList)

        projectListView.adapter = mAdapter

    }



//    서버에 프로젝트 목록 요청/분석 기능 함수
    fun getProjectsFromServer(){

//        실제 서버 호출 등 작업
        ServerUtil.getProjectList(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                val dataObj = json.getJSONObject("data") //중괄호{}
                val projectsArr = dataObj.getJSONArray("projects") // 대괄호[]
//                ex. 0~9(10직전)까지 for문
                for (i in 0 until projectsArr.length()){
//                    projectArr에서 자리에 맞는 i번째 { } JSONObject 추출
                    val projectObj = projectsArr.getJSONObject(i)

//                  projectObj는 JSONObject. => Project로 변환해서 ArrayList에 add
                    var project = Project.getProjectFromJSON(projectObj)

//                    완성된 프로젝트 mProjectList에 추가.
                    mProjectArrayList.add(project)
                }

//                서버를 다녀오는 행위이므로 어댑터 연결보다 우선 작성 되어도 실제로는 늦게 끝날 수 있다.
                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                }

            }
        })
    }
}