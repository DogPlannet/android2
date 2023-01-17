package com.example.umc_pj

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class BreedAdapter(var persons: Array<BreedDTO>, var con: Context) :
    RecyclerView.Adapter<BreedAdapter.ViewHolder>() {
    var TAG = "PhoneBookListAdapter"

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var iv_person_phone_book_list_item: ImageView
        lateinit var tv_name_phone_book_list_item: TextView
        lateinit var tv_phone_number_phone_book_list_item: TextView

        init {
            iv_person_phone_book_list_item = itemView.findViewById(R.id.iv_person_phone_book_list_item)
            tv_name_phone_book_list_item = itemView.findViewById(R.id.tv_name_phone_book_list_item)
            tv_phone_number_phone_book_list_item = itemView.findViewById(R.id.tv_phone_number_phone_book_list_item)

            itemView.setOnClickListener {
                AlertDialog.Builder(con).apply {
                    var position = adapterPosition
                    var person = persons[position]
                    setTitle(person.name)
                    setMessage(person.phoneNumber)
                    setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(con, "OK Button Click", Toast.LENGTH_SHORT).show()
                    })
                    show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dogbreed_fragment, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person: BreedDTO = persons[position]
        //[수정요함] 이미지 작업의 경우 glide를 사용해 server의 image를 불러올 것
        //holder.iv_person_phone_book_list_item
        holder.tv_name_phone_book_list_item.text = person.name
        holder.tv_phone_number_phone_book_list_item.text = person.phoneNumber
    }

    override fun getItemCount(): Int {
        return persons.size
    }


}