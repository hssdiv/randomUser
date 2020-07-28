package com.div.randomuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_item.view.*

class UserRecyclerViewAdapter(
    private val userList: List<User>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,
            parent, false)
        return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]

        Glide.with(holder.itemView.context)
            .load(currentItem.imageUrl)
            .circleCrop()
            .into(holder.userImage)
        holder.userName.text = currentItem.name

    }

    override fun getItemCount() = userList.size

    inner class UserViewHolder(userView: View) : RecyclerView.ViewHolder(userView),
    View.OnClickListener{
        val userImage: ImageView = userView.userImage
        val userName: TextView = userView.userName

        init {
            userView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            //if (position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position)
            //}
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}