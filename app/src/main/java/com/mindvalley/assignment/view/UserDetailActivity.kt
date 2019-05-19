package com.mindvalley.assignment.view

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import com.mindvalley.assignment.R
import com.mindvalley.assignment.model.UserInfoDTO
import com.mindvalley.assignment.downloader.FetchBitmap
import kotlinx.android.synthetic.main.activity_detail.*

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (intent.extras.getSerializable("userInfoDTO") as UserInfoDTO).let {
            bindDataToViews(it)
        }
    }

    private fun bindDataToViews(userInfoDTO: UserInfoDTO){
        pict.setBackgroundColor(Color.parseColor(userInfoDTO.color))
        url.text=userInfoDTO.url
        created.text=userInfoDTO.createdAt
        user.text=userInfoDTO.userName
        dimentions.text = "${userInfoDTO.width}x${userInfoDTO.height}"

        ViewCompat.setTransitionName(pict, getString(R.string.image_item_for_transition))

        FetchBitmap(userInfoDTO.url).load { result, throwable->
            if(throwable!=null)
                pict.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.error))
            else
                pict.setImageBitmap(result!!)
        }
    }
}
