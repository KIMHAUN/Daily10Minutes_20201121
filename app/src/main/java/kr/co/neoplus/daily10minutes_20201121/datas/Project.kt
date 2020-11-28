package kr.co.neoplus.daily10minutes_20201121.datas

import org.json.JSONObject
import java.io.Serializable

class Project : Serializable {

    var id = 0 //id에는 정수가 들어올 거라고 명시
    var title = "" //title 에는 String이 들어옴.
    var imageURL = ""
    var description = ""
    var proofMethod = ""
    var completeDays = 0 // 완주 횟수
    var onGoingUsersCount = 0 //진행중인 유저 수
    var isMyProject = false //참여중인 프로젝트인지 boolean값
    
    companion object{
        
//        적당한 모양의 JSON {}를 넣으면 => Project 객체로 변환해 주는 기능.
//        액티비티 등 다른 곳에서는 이 기능을 가져다 사용만 하자.
        
        fun getProjectFromJSON(json: JSONObject) : Project{
//            기본 데이터만 갖고 있는 Project() 생성
            val project = Project()
            
//            재료로 들어온 JSON내용을 파싱해서 project 변수의데이터 교체
            project.id = json.getInt("id")
            project.title = json.getString("title")
            project.imageURL = json.getString("img_url")
            project.description = json.getString("description")
            project.proofMethod = json.getString("proof_method")
            project.completeDays = json.getInt("complete_days")
            project.onGoingUsersCount = json.getInt("ongoing_users_count")

            project.isMyProject = json.getBoolean("is_my_project")
            
//            완성된 project 변수 리턴
            return project 
        }        
    }

}