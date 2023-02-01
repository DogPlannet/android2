package com.example.umc_pj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.umc_pj.databinding.FragmentNaviMypageBinding
import kotlinx.android.synthetic.main.fragment_navi_mypage.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

val serviceFragment = ServiceFragment()
val changePwFragment = ChangePwFragment()
val wroteFragment = WroteFragment()
val questionFragment = ManyQuestionFragment()


class NaviMypageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentNaviMypageBinding

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
//        _binding = FragmentNaviMypageBinding.inflate(inflater, container, false)
//        val view = binding.root
//        return view
        binding =  FragmentNaviMypageBinding.inflate(inflater, container, false)

        binding.serviceDetailBtn.setOnClickListener{
//            childFragmentManager.beginTransaction().apply {
//                replace(R.id.my_page_fr, serviceFragment)
//                addToBackStack(null)
//                commit()
//            }
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main_frm, serviceFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.changePwBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main_frm, changePwFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.writeDetailBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main_frm, wroteFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.questionBtn.setOnClickListener{
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.main_frm, questionFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//
//        service_detail_btn.setOnClickListener {
//            (parentFragment as? NaviMypageFragment)?.service()
//            Log.d("dd", "클릭됨")
//        }
    }

    // fragment 액션바 없애주기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

//    private fun service() {
//        childFragmentManager.beginTransaction()
//            .replace(R.id.service_fragment, serviceFragment)
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
//    }
//
//    fun serviceDetail1() {
//        childFragmentManager.beginTransaction()
//            .replace(R.id.service_fragment_detail1, serviceDetail1Fragment)
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NaviMypageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NaviMypageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}