package kr.co.neoplus.daily10minutes_20201121.datas

import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class User : Serializable {
    var id = 0 // id : int
    var email = ""
    var nickName = ""

//    응용
//    1. 가입 일시(created_at) 서버는 String으로 알려줌. 앱에서는 Calendar 형태로 변환해서 사용.

//    Calendar를 만들 때는, 생성자(Calendar()) 사용 x. 인스턴스 사용
//    createdAt기본 값 : 현재 시간 정보보
   val createdAt = Calendar.getInstance()


//    2. 프로필 사진들 : 한 명의 유저가 여러 장의 사진URL (String)을 갖고 있음을 어떻게 표현하는가?

//    한 명의 사용자 : 프사 URL 목록을 갖는다고 명시. (ArrayList를 갖는다.)
    val profileImgList = ArrayList<String>()


//    JSON넣으면 User로 변환해주는 기능 제작
    companion object{
        fun getUserFromJSON(json : JSONObject) : User{
            val u = User()
//            u의 변수들을 json이 가진 값을 활용해서 변경
            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickName = json.getString("nick_name")

//            프사 목록 파싱 u가 가진 profileImgList에 add해주자.
            val profileImagesArr = json.getJSONArray("profile_images")
            for(i in 0 until profileImagesArr.length()){
                val pf = profileImagesArr.getJSONObject(i)
                val imgURL = pf.getString("img_url")
                u.profileImgList.add(imgURL)
            }

//            사용자의 회원가입 일자를 2000-01-01 00:00:00으로 세팅하고싶다.

//            1) 항목을 지정하고 거기에 들어갈 값 명시
            u.createdAt.set(Calendar.YEAR, 2000) //년도 자리에 2000
            u.createdAt.set(Calendar.MONTH, Calendar.JANUARY) //0~11월 Calendar.Janu
            u.createdAt.set(Calendar.DAY_OF_MONTH, 1) //(월 중에 몇 번쩨 일인지) 일자 자리

//            2) 연/월/일 등 일괄 입력 여러 항목을 한꺼번에 입력하기
            u.createdAt.set(2000, Calendar.JANUARY, 1, 0, 0, 0)

//            3) Calendar 내부의 time 변수를 통째로 변경 -> 서버 데이터 파싱시 활용.

//            서버가 주는 String을 우선 저장
            val createdAtStr = json.getString("created_at")

//            Calendar에 들어갈 수 있게 해주자. String 변환(SimpleDateFormat)
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //이 양식으로 된 걸 분석.

//            sdf를 이용해서 createdAtStr를 Calendar(u.createdAt)에 대입.

//            분석 결과 : Date?로 나옴.Calendar 아님.
            val parsedTime = sdf.parse(createdAtStr)

//            Calendar에 Date를 어떻게 대입하누냐 ~~ => Calendar가 가진 time 변수에 대임
            u.createdAt.time = parsedTime
            return u
        }
    }
}