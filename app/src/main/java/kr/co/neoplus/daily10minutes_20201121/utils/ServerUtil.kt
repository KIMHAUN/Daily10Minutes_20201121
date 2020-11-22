package kr.co.neoplus.daily10minutes_20201121.utils

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class ServerUtil {

//    ServerUtil.함수()처럼, 클래스 이름.만해도 기능을 사용하게 도와주는 코드.
//    JAVA - static 개념에 대응되는 개념.
    companion object{
//      서버 호스트 주소를 쉽게 입력하기 위한 변수
        val BASE_URL = "http://15.164.153.174"
//      로그인 기능 수행 함수.

        fun postRequestLogin(id:String, pw:String){
//            클라이언트의 역할을 수행해주는 변수(라이브러리 활용)
            val client = OkHttpClient()

//            실제 기능 수행 주소 : ex) 로그인 - http://15.164.153.174/user
//            BASE_URL/user

            val urlString = "${BASE_URL}/user"

//            POST 메소드 - 보통 formBody(OkHttp 라이브러리가 제공) 에 데이터 첨부
//            Builder의 마무리 .build() 등
            val formData = FormBody.Builder()
                    .add("email", id)
                    .add("password", pw)
                    .build()

//            myIntent 변수를 만들듯이 API Request 정보를 담는 변수도 만들어야 함.
//            요청에 대한 모든 정보 종합하는 역할.
//            1. 어디로? url
//            2. 어떤 방식? method(POST)
//            3. 갈 때 어떤 데이터? formData에 담아둠.
//            + header 데이터 요청이 있다면 여ㅣ서 추가.

            val request = Request.Builder()
                    .url(urlString)
                    .post(formData)
//                    .header("이름표", "실제 값") - 서버가 헤더 데이터를 요구하면 주석 해제.
                    .build()

//            startActivity처럼 실제로 Request를 실행시키는 함수.
//            클라이언트로 동작하는 행위(Request 호출)
//            OktHttp 라이브러리 => client 변수 활용
            client.newCall(request)









        }
    }
}