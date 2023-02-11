package com.example.umc_pj

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.view.*
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_pj.databinding.FragmentNaviCommunityBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_navi_community.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [NaviCommunityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NaviCommunityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: NaviCommunityAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<NaviCommunityModel>

    lateinit var title : Array<String>
    lateinit var nickname : Array<String>
    lateinit var dog : Array<String>
    lateinit var heartcnt : Array<String>
    lateinit var commentcnt : Array<String>

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

        return inflater.inflate(R.layout.fragment_navi_community, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NaviCommunityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NaviCommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // fragment 액션바 보여주기(선언안해주면 다른 프레그먼트에서 선언한 .hide() 때문인지 모든 프레그먼트에서 액션바 안보임
        (activity as AppCompatActivity).supportActionBar?.show()

        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.community_rc_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = NaviCommunityAdapter(newsArrayList)
        recyclerView.adapter = adapter

        btn_create_post.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, PostingActivity()::class.java))
            }
        }
    }


    private fun dataInitialize(){
        newsArrayList = arrayListOf<NaviCommunityModel>()

        title = arrayOf(
            getString(R.string.head_1),
            getString(R.string.head_2),
            getString(R.string.head_3),
            getString(R.string.head_4),
            getString(R.string.head_5),
            getString(R.string.head_6),
            getString(R.string.head_7),
            getString(R.string.head_8),
            getString(R.string.head_9),
            getString(R.string.head_10),
        )

        nickname = arrayOf(
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
            getString(R.string.nick_1),
        )

        dog = arrayOf(
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
            getString(R.string.dog_1),
        )

        heartcnt = arrayOf(
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
            getString(R.string.heartcnt_1),
        )

        commentcnt = arrayOf(
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
            getString(R.string.commentcnt_1),
        )

        for(i in title.indices){
            val news = NaviCommunityModel(title[i], nickname[i], dog[i], heartcnt[i], commentcnt[i])
            newsArrayList.add(news)
        }


    }

}