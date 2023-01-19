package com.example.umc_pj

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class BreedAdapter(var onItemClick: BreedItemClick, var persons: ArrayList<BreedDTO>, var con: Context) :
    RecyclerView.Adapter<BreedAdapter.ViewHolder>(),Filterable  {
    var filteredBreed = ArrayList<BreedDTO>()
    var itemFilter = ItemFilter()
    var choose_breed = String()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_name_phone_book_list_item: TextView
        init {
            tv_name_phone_book_list_item = itemView.findViewById(R.id.breed_name)

            itemView.setOnClickListener {
                var position = adapterPosition
                choose_breed = filteredBreed[position].name
                onItemClick.onClick(choose_breed)
            }
        }
    }
    init {
        filteredBreed.addAll(persons)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dogbreed_fragment, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person: BreedDTO = filteredBreed[position]
        holder.tv_name_phone_book_list_item.text = person.name
    }

    override fun getItemCount(): Int {
        return filteredBreed.size
    }

    override fun getFilter(): Filter {
        return itemFilter
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d("gdgd", "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<BreedDTO> = ArrayList<BreedDTO>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                filteredList.add(filteredBreed[0])
                results.values = persons
                results.count = persons.size
                return results

                //공백제외 2글자 이인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                filteredList.add(filteredBreed[0])
                for (person in persons) {
                    if (person.name.contains(filterString)) {
                        filteredList.add(person)
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
            } else {
                filteredList.add(filteredBreed[0])
                for (person in persons) {
                    if (person.name.contains(filterString)) {
                        filteredList.add(person)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredBreed.clear()
            filteredBreed.addAll(filterResults.values as ArrayList<BreedDTO>)
            notifyDataSetChanged()
        }
    }
}