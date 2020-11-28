package kr.co.neoplus.daily10minutes_20201121.datas

import java.io.Serializable

class Project : Serializable {

    var id = 0 //id에는 정수가 들어올 거라고 명시
    var title = "" //title 에는 String이 들어옴.
    var imageURL = ""
    var description = ""
    var proofMethod = ""
    var completeDays = 0 // 완주 횟수
    var onGoingUsersCount = 0 //진행중인 유저 수

}