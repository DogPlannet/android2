package com.example.umc_pj

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NaviCommunityAdapter(private val itemList: ArrayList<NaviCommunityModel>):
        RecyclerView.Adapter<NaviCommunityAdapter.NaviCommunityViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaviCommunityViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_community_rc_view_item, parent, false)
                return NaviCommunityViewHolder(view)
        }

        override fun getItemCount(): Int {
                return itemList.count()
        }

        override fun onBindViewHolder(holder: NaviCommunityViewHolder, position: Int) {
                holder.title.text = itemList[position].title
                holder.nickname.text = itemList[position].nickname
                holder.dog.text = itemList[position].dog
                holder.heartcnt.text = itemList[position].heartcnt
                holder.commentcnt.text = itemList[position].commentcnt
        }

        class NaviCommunityViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
                val title = itemView.findViewById<TextView>(R.id.tv_title)
                val nickname = itemView.findViewById<TextView>(R.id.tv_nickname)
                val dog = itemView.findViewById<TextView>(R.id.tv_dog)
                val heartcnt = itemView.findViewById<TextView>(R.id.tv_cnt_heart)
                val commentcnt = itemView.findViewById<TextView>(R.id.tv_cnt_comment)
        }

}