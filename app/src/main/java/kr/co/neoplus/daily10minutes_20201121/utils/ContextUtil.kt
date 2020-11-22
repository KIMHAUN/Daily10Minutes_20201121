package kr.co.neoplus.daily10minutes_20201121.utils

import android.content.Context

class ContextUtil {
    companion object{

//       항목 하나마다 2가지 기능 필요 SAVE(SETTER), LOAD(GETTER)
//       파일 이름을 오타내지 않이 위해 변수로 저장(메모장 이름 역할)
        val prefName = "Daily10MinutesPref"

//        로그인한 사용자의 토큰을 저장한다는 의미의 항목명 변수화.
        val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

//        사용자 토큰 저장 / 불러내기 함수 2개

//        SETTER 저장

        fun setLoginUserToken(context: Context, token: String) {
            //mode 다른 앱하고 공유할건지 => X
//            메모장 파일을 (파일이름 : 변수 활용, 모드 : 우리 앱에서만 활용) 열어서 pref 변수에 저장.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            열린 메모장(pref)에 token 값(LOGIN_USER_TOKEN 항목에) 저장
//            apply()로 최종 SAVE
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }
//        GETTER 불러내가
        fun getLoginUserToken(context: Context) : String{
//          메모장을 열어서(SAVE때와 동일) LOGIN_USER_TOKEN에 저장된 String 을 결과로 지정
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            //getString return이 String?임.
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }

    }
}