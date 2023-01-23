package com.example.umc_pj.homepackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.FragmentTransaction
import com.example.umc_pj.MainActivity
import com.example.umc_pj.NaviCommunityFragment
import com.example.umc_pj.R
import com.example.umc_pj.databinding.ActivityAgreeBinding
import com.example.umc_pj.databinding.FragmentHomeRecordBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    class HomeRecord : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_home_record, container, false)
        }


        //replace코드
//        override fun onActivityCreated(savedInstanceState: Bundle?) {
//            super.onActivityCreated(savedInstanceState)
//            binding.test.setOnClickListener {
//                childFragmentManager.beginTransaction()
//                    .replace(R.id.main_frm, NaviCommunityFragment())
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//            }
//        }
    }