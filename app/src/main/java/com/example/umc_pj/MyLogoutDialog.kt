package com.example.umc_pj

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.umc_pj.databinding.LogoutdialogBinding

class MyLogoutDialog(context: NaviMypageFragment, logoutDialog: NaviMypageFragment) : Dialog(context) {

    private var mBinding : LogoutdialogBinding? = null
    private val binding get() = mBinding!!

    private var logoutDialog:LogoutDialog? = null

    // 인터페이스 연결
    init {
        this.logoutDialog = logoutDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LogoutdialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배경 투명하게
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.yesBtn.setOnClickListener {
            this.logoutDialog?.onSubscribeBtnClicked()
        }
        binding.noBtn.setOnClickListener {
            this.logoutDialog?.onLikedBtnClicked()
        }
    }
}

