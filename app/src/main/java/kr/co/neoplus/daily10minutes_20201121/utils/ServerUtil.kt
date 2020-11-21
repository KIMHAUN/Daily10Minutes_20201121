package kr.co.neoplus.daily10minutes_20201121.utils

import okhttp3.OkHttpClient

class ServerUtil {

//    ServerUtil.함수()처럼, 클래스 이름.만해도 기능을 사용하게 도와주는 코드.
//    JAVA - static 개념에 대응되는 개념.
    companion object{
//      서버 호스트 주소를 쉽게 입력하기 위한 변수
        val BASE_URL = "http://15.164.153.174"
//      로그인 기능 수행 함수.

        fun postRequestLogin(email:String, password:String){
//            클라이언트의 역할을 수행해주는 변수(라이브러리 활용)
            val client = OkHttpClient()

//            실제 기능 수행 주소 : ex) 로그인 - http://15.164.153.174/user
//            BASE_URL/user

            val urlString = "${BASE_URL}/user"



        }
    }
}