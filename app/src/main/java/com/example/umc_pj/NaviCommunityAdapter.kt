package com.example.umc_pj

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

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

                holder.itemView.setOnClickListener(object : View.OnClickListener{
                        override fun onClick(p0: View?) {
                                val activity=p0!!.context as AppCompatActivity
                                val activity2 = p0!!.context as MainActivity
                                val communityItemFragment = CommunityItemFragment()
                                activity.supportFragmentManager.beginTransaction().replace(R.id.main_frm, communityItemFragment)
                                        .addToBackStack(null).commit()
                                activity.toolbar.visibility = View.INVISIBLE
                                activity.toolbar3.visibility = View.VISIBLE
                        }
                })
        }

        class NaviCommunityViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){
                val title = itemView.findViewById<TextView>(R.id.tv_title)
                val nickname = itemView.findViewById<TextView>(R.id.tv_nickname)
                val dog = itemView.findViewById<TextView>(R.id.tv_dog)
                val heartcnt = itemView.findViewById<TextView>(R.id.tv_cnt_heart)
                val commentcnt = itemView.findViewById<TextView>(R.id.tv_cnt_comment)
        }

}