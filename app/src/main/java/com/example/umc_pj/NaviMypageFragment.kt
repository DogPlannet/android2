package com.example.umc_pj

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.FragmentNaviMypageBinding
import com.example.umc_pj.homepackage.MyDialog
import com.example.umc_pj.homepackage.CustomDialog as CustomDialog1

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
        binding = FragmentNaviMypageBinding.inflate(inflater, container, false)

        binding.serviceDetailBtn.setOnClickListener{
//            childFragmentManager.beginTransaction().apply {
//                replace(R.id.my_page_fr, serviceFragment)
//                addToBackStack(null)
//                commit()
//            }
            parentFragmentManager.beginTransaction().apply {
                setCustomAnimations(R.anim.to_right, R.anim.to_right)
                    .replace(R.id.main_frm, serviceFragment)
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

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.logout.setOnClickListener{
            onClick(view)
        }
        binding.withdraw.setOnClickListener{
            onClickWithdraw(view)
        }
        binding.email.setOnClickListener{
            onClickEmail(view)
        }
    }

    // fragment 액션바 없애주기
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun onClick(view: View?) {
        val logoutDlg = LogoutDialog(mainActivity)
        Log.d("gdsa","asdgdsg")
        logoutDlg.setOnOKClickedListener{ content ->
            binding.logout.text = content
        }
        logoutDlg.show("메인의 내용을 변경할까요?")
    }

    private fun onClickWithdraw(view: View?) {
        val deleteDlg = DeleteDialog(mainActivity)
        deleteDlg.setOnOKClickedListener{ content ->
            binding.withdraw.text = content
        }
        deleteDlg.show("회원탈퇴")
    }

    private fun onClickEmail(view: View?) {
        val emailDlg = EmailDialog(mainActivity)
        emailDlg.setOnOKClickedListener { content ->
            binding.email.text = content
        }
        emailDlg.show("이메일")
    }

    companion object {
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