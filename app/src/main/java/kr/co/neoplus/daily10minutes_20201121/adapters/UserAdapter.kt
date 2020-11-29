package kr.co.neoplus.daily10minutes_20201121.adapters

import android.content.Context
import android.media.Image
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.neoplus.daily10minutes_20201121.R
import kr.co.neoplus.daily10minutes_20201121.datas.Project
import kr.co.neoplus.daily10minutes_20201121.datas.User
import java.text.SimpleDateFormat

class UserAdapter(
    val mContext:Context, 
    resId : Int,
    val mList: List<User>) : ArrayAdapter<User>(mContext, resId, mList) {
//  mContext, mList => m으로 시작 : 멤버변수(클래스 내부 어디에서든 사용 가능한 변수) 명시
//  resId => 그냥 시작 : 멤버변수가 아님.(다른 함수에서는 사용할 수 없다.)  클래스 첫 중괄호까지 OK
    
    val mInf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        리스트뷰의 재사용성 활용 : convertView 변수 활용
        var tempRow = convertView
        if(tempRow == null){
            tempRow = mInf.inflate(R.layout.user_list_item, null)
        }
        val row = tempRow!!

        val profileImg = row.findViewById<ImageView>(R.id.profileImg)
        val nickNameTxt = row.findViewById<TextView>(R.id.nickNameTxt)
        val emailTxt = row.findViewById<TextView>(R.id.emailTxt)
        val signUpDateTxt = row.findViewById<TextView>(R.id.signUpDateTxt)

        val userData = mList[position]

        nickNameTxt.text = userData.nickName
        emailTxt.text = userData.email

        Glide.with(mContext)
            .load(userData.profileImgList[0])
            .into(profileImg)

//        가입 일자(userData.createdAt)를 => "2000-01-01" 양식으로 가공하고싶다.
//        양식 가공 : SimpleDateFormat 활용

//        가공할 양식 결정
        val sdf = SimpleDateFormat("yyyy-MM-dd")

//        해당 양식 활용해서 Calendar를 String으로 뽑아내자.
        val createdAtStr = sdf.format(userData.createdAt.time)

//        가입일자 문구에 반영
        signUpDateTxt.text = "(가입일자 : ${createdAtStr})"

        return row
    }
}