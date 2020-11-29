package kr.co.neoplus.daily10minutes_20201121.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCM : FirebaseMessagingService(){

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
//      (라이브러리가 알아서) 새 토큰을 발급받았을 때 실행되는 함수
//        토큰값 임시 확인용
        Log.d("새로 발급된 기기토큰", p0)

    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
//        FCM서버가 우리 폰으로 푸시알람을 줬을 때 실행되는 함수
//        푸시 알림의 내용 : p0에 들어있음.
//        실제 사용자에게 알림을 띄우는 코드를 작성하는 곳


    }
}