package com.mindvalley.assignment.adapter

import android.content.Context
import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mindvalley.assignment.R
import com.mindvalley.assignment.model.UserInfoDTO
import com.mindvalley.assignment.viewholder.ItemViewHolder
import kotlinx.android.synthetic.main.pin_layout.view.*

class ItemAdapter(var context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.pin_layout, parent, false)
        val holder = ItemViewHolder(view)
        holder.imageView = view.pict
        holder.textView = view.nameTv
        return holder
    }

    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemViewHolder
        val item =  listDiffer.currentList[position]
        itemHolder.bind(item)
    }

    private val listDiffer = AsyncListDiffer(this, object: DiffUtil.ItemCallback<UserInfoDTO>() {
        override fun areItemsTheSame(oldItem: UserInfoDTO, newItem: UserInfoDTO): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
                oldUserInfoDTO: UserInfoDTO, newUserInfoDTO: UserInfoDTO):Boolean {
            return oldUserInfoDTO.likedByUser==newUserInfoDTO.likedByUser &&
                    oldUserInfoDTO.color==newUserInfoDTO.color &&
                    oldUserInfoDTO.url==newUserInfoDTO.url &&
                    oldUserInfoDTO.width==newUserInfoDTO.width &&
                    oldUserInfoDTO.height==newUserInfoDTO.height &&
                    oldUserInfoDTO.createdAt==newUserInfoDTO.createdAt
        }
    })

    fun sendList(userInfoDTOS: List<UserInfoDTO>)
    {
        listDiffer.submitList(userInfoDTOS)
    }
}
