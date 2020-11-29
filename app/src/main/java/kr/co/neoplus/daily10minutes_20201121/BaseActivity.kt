package kr.co.neoplus.daily10minutes_20201121

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("모든 화면", "공통 기능 자동 실행")

//        모든 화면이 공통적으로 실행하는 함수에서 액션바 설정
//        모든 화면에 커스텀 액션바 자동 반영
        //? null이 아닐 때만 실행해주세요.
        supportActionBar?.let {
//            supportActionBar가 null이 아닐 때 실행시켜줄 코드. : let의 역할.
            setCustomActionBar()

        }
    }

    val mContext = this
    //this 대신 mContext사용.

    //overiding 무조건 구현해서 사용해야됨.
    abstract fun setupEvents()
    abstract fun setValues()

    //    액션바를 직접 그리기 위한 함수
    fun setCustomActionBar(){
//        1. 액션바가 어떻게 보이게 하고싶은지? 모양(LAYOUT)을 그려야함. XML작성

//        기존 액션바를 불러내서 속성((커스텀 액션바 모드)들을 변경. => xml 반영

//        기존 액션바 불러내기(무조건 존재한다고 우기기)
        val defaultActionBar = supportActionBar!!

//    커스텀 액션바를 보여주게 모드 변경
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

//    실제로 보여줄 커스텀 화면 연결.
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

//    액션바(보라색) > 툴바(여백) > 커스텀 뷰(검정 배경)
//    여백을 없애려면 패딩같은걸 0으로 설정. 이름이 패딩은 아님. 액션바의 툴바의 속성 변경

//    액션바의 커스텀뷰의 부모를 툴바(androidx의 툴바)로 캐스팅
        val toolbar = defaultActionBar.customView.parent as Toolbar //androidx의 toolbar
//    insets : 내부 값
        toolbar.setContentInsetsAbsolute(0, 0)

    }

}