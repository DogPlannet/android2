package com.example.umc_pj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.umc_pj.databinding.FragmentNaviMypageBinding
import com.example.umc_pj.databinding.FragmentServiceBinding
import kotlinx.android.synthetic.main.fragment_navi_mypage.*
import kotlinx.android.synthetic.main.fragment_service.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var MypageFragment = NaviMypageFragment()

class ServiceFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentServiceBinding

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
        binding =  FragmentServiceBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener{
            Log.d("dd", "클릭됨")
//            childFragmentManager.beginTransaction().apply {
//                replace(R.id.my_page_fr, serviceFragment)
//                addToBackStack(null)
//                commit()
//            }
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main_frm, MypageFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
//        btn3.setOnClickListener {
//            (parentFragment as NaviMypageFragment).serviceDetail1()
//        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ServiceFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ServiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}