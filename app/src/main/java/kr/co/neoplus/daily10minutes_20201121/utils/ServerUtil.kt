package kr.co.neoplus.daily10minutes_20201121.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    직접 만든 가이드북
//    화면(액티비티)입장에서, 서버에 다녀왔을 때 어떤 행동을 할지
//    행동 지침을 담아주기 위한 인터페이스(가이드북)를 직접 정듸
    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

//    ServerUtil.함수()처럼, 클래스 이름.만해도 기능을 사용하게 도와주는 코드.
//    JAVA - static 개념에 대응되는 개념.
    companion object{
//      서버 호스트 주소를 쉽게 입력하기 위한 변수
        val BASE_URL = "http://15.164.153.174"
//      로그인 기능 수행 함수.

        fun postRequestLogin(context: Context, id:String, pw:String, handler : JsonResponseHandler?){
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

//            돌아왔을 때 할 가이드북 작성(object)
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체 실패(인터넷 단선, 서버 터짐 등의 사유)
                    Toast.makeText(context, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call, response: Response) {
//                    서버가 내려준 응답에 뭐라고 적혀있는지 확인
//                    응답(Response) - 본문(body) + 부가정보들 => body만 추출.
//                    String 형태로 변환해서 저장(로그로 확인) toString말고 string()
                    val bodyString = response.body!!.string()

//                  bodyString은 JSON 양식으로 가공되어 내려옴.\ub85c\uadf8\uc778 \uc131\uacf5.
//                    앱에서도 JSON을 다룰 수 있도록 bodystring => JSON
//                    Log.d("서버응답 본문", bodyString)
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답 본문", jsonObj.toString())

//                    이 함수를 사용하는 화면에서 서버에 다녀오면 어떻게 대처할지 적어두었다면
//                    그 내용을 실행하도록 해주는 코드
                    handler?.onResponse(jsonObj)

                }
            })
        }
    }
}