package com.example.umc_pj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoticeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoticeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: NoticeAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<NoticeModel>

    lateinit var title : Array<String>
    lateinit var comment : Array<String>
    lateinit var date : Array<String>
    lateinit var time : Array<String>
    lateinit var icon : Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoticeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoticeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.notice_rc_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = NoticeAdapter(newsArrayList)
        recyclerView.adapter = adapter
    }

    private fun dataInitialize(){
        newsArrayList = arrayListOf<NoticeModel>()

        title = arrayOf(
            getString(R.string.notice_title),
            getString(R.string.notice_title),
            getString(R.string.notice_title),
            getString(R.string.notice_title),
        )

        comment = arrayOf(
            getString(R.string.notice_comment_1),
            getString(R.string.notice_comment_2),
            getString(R.string.notice_comment_3),
            getString(R.string.notice_comment_4),
        )

        date = arrayOf(
            getString(R.string.notice_date_1),
            getString(R.string.notice_date_2),
            getString(R.string.notice_date_3),
            getString(R.string.notice_date_4),
        )

        time = arrayOf(
            getString(R.string.notice_time),
            getString(R.string.notice_time),
            getString(R.string.notice_time),
            getString(R.string.notice_time),
        )

        icon = arrayOf(
            R.drawable.new_notice_icon,
            R.drawable.new_notice_icon,
            R.drawable.new_notice_icon_none,
            R.drawable.new_notice_icon_none,
        )

        for(i in title.indices){
            val news = NoticeModel(title[i], comment[i], date[i], time[i], icon[i])
            newsArrayList.add(news)
        }

    }
}