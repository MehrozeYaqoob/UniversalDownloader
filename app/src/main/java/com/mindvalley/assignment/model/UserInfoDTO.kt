package com.mindvalley.assignment.model

import android.util.Log
import com.google.gson.JsonObject
import java.io.Serializable

data class UserInfoDTO(var id:String ="",
                       var createdAt:String = "",
                       var color:String = "#000000",
                       var width:Int = 0,
                       var height:Int = 0,
                       var likedByUser:Boolean =false,
                       var userName:String = "",
                       var url:String =""):Serializable
{
    companion object {
        fun fromJsonObject(data: JsonObject): UserInfoDTO
        {
            val pin = UserInfoDTO()
            try {
                pin.id = data["id"].asString
                pin.createdAt = data["created_at"].asString
                pin.color = data["color"].asString
                pin.width = data["width"].asInt
                pin.height = data["height"].asInt
                pin.likedByUser = data["liked_by_user"].asBoolean
                pin.userName = data["user"].asJsonObject["name"].asString
                pin.url = data["urls"].asJsonObject["regular"].asString
            }
            catch (e:Exception)
            {
                //If error return a default pin
                Log.e("e","Json Object contains not valid pin data")
            }

            return pin
        }
    }
}