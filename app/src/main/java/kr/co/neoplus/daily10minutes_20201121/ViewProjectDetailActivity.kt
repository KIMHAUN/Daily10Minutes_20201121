package kr.co.neoplus.daily10minutes_20201121

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.neoplus.daily10minutes_20201121.datas.Project
import kr.co.neoplus.daily10minutes_20201121.utils.ContextUtil
import kr.co.neoplus.daily10minutes_20201121.utils.ServerUtil
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        //나도 도전하기!
        applyBtn.setOnClickListener {
            ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    val dataObj = json.getJSONObject("data")
                    val projectObj = dataObj.getJSONObject("project")

//                    참여신청하면 최신상태로 변경된 프로젝트 정보를 서버가 다시 알려줌.
//                    그 모든 정보가 담겨있는 새 프로잭트 json : projectObj

                    val newProject = Project.getProjectFromJSON(projectObj)

//                    화면에는 mProject가 뿌려짐
                    mProject = newProject

//                    새 프로젝트 데이터를 다시 뿌려주자. UI 변경
                    runOnUiThread {
                        setProjectDataToUI()
                    }
                }
            })
        }
    }

    override fun setValues() {
        mProject = intent.getSerializableExtra("project") as Project
        setProjectDataToUI()

    }

//    mProject로 화면에 뿌려주는 함수
    fun setProjectDataToUI(){
    //        mProject 활용 => 실제 데이터 화면에 뿌려주기.

        projectTitleTxt.text = mProject.title
        Glide.with(mContext).load(mProject.imageURL).into(projectImg)
        projectDescription.text = mProject.description
        projectProofMethodTxt.text = mProject.proofMethod

    //        서버가 주는 데이터 : 5 숫자 ->String 가공
        onGoingUsersCountTxt.text = "지금 ${mProject.onGoingUsersCount}명 참여중!"
    }
}