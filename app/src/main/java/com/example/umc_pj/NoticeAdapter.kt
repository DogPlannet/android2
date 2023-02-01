package com.example.umc_pj

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoticeAdapter(private val itemList: ArrayList<NoticeModel>):
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_notice_rc_view_item, parent, false)
        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.title.text = itemList[position].title
        holder.comment.text = itemList[position].comment
        holder.date.text = itemList[position].date
        holder.time.text = itemList[position].time
        holder.icon.setImageDrawable(holder.icon.context.getDrawable(itemList[position].icon))
        holder.layout.setImageDrawable(holder.layout.context.getDrawable(itemList[position].layout))
    }

    class NoticeViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tv_title_notice)
        val comment = itemView.findViewById<TextView>(R.id.tv_comment_notice)
        val date = itemView.findViewById<TextView>(R.id.tv_date_notice)
        val time = itemView.findViewById<TextView>(R.id.tv_time_notice)
        val icon = itemView.findViewById<ImageView>(R.id.new_notice_icon)
        val layout = itemView.findViewById<ImageView>(R.id.notice_layout)
    }

}