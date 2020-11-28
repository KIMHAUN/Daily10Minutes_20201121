package kr.co.neoplus.daily10minutes_20201121.datas

import java.io.Serializable

class User : Serializable {
    var id = 0 // id : int
    var email = ""
    var nickName = ""

//    응용
//    1. 가입 일시(created_at) 서버는 String으로 알려줌. 앱에서는 Calendar 형태로 변환해서 사용.
//    2. 프로필 사진들 : 한 명의 유저가 여러 장의 사진URL (String)을 갖고 있음을 어떻게 표현하는가?


}