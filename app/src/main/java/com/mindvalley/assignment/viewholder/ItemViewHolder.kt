package com.mindvalley.assignment.viewholder

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mindvalley.assignment.R
import com.mindvalley.assignment.model.UserInfoDTO
import com.mindvalley.assignment.view.UserDetailActivity
import com.mindvalley.assignment.downloader.Downloader
import com.mindvalley.assignment.downloader.FetchBitmap
import java.io.Serializable


class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    lateinit var imageView: ImageView
    private var downloader: Downloader<*>? = null
    lateinit var textView: TextView

    fun bind(userInfoDTO: UserInfoDTO)
    {
        downloader?.cancel()
        imageView.setImageDrawable(null)

        textView.text = userInfoDTO.userName
        imageView.setBackgroundColor(Color.parseColor(userInfoDTO.color))

        imageView.post {
            if(userInfoDTO.width < imageView.width)
            {
                imageView.minimumHeight=userInfoDTO.height
                return@post
            }

            val scale = userInfoDTO.width.toFloat()/imageView.width.toFloat()
            val scaledHeight = userInfoDTO.height.toFloat()/scale
            imageView.minimumHeight=scaledHeight.toInt()

        }

        //load image using library
        val down = FetchBitmap(userInfoDTO.url)
        downloader = down
        down.load {result, throwable->
            if(throwable!=null)//if error show placeholder
            {
                Log.e("Placeholder","Exception: $throwable")
                imageView.setImageBitmap(BitmapFactory.decodeResource(itemView.resources, R.drawable.error))
            }
            else//show loaded image
                imageView.setImageBitmap(result!!)
        }

        //attach events to open userInfoDTO details
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, UserDetailActivity::class.java).putExtra("userInfoDTO",userInfoDTO as Serializable)
            itemView.context.startActivity(intent)

        }
    }
}