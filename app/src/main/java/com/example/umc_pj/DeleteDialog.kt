package com.example.umc_pj

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.DeletecompletedialogBinding
import com.example.umc_pj.databinding.DeletedialogBinding
import com.example.umc_pj.databinding.LogoutcompletedialogBinding


class DeleteDialog(private val context : AppCompatActivity) {
    private lateinit var listener : MyDialogOKClickedListener
    private lateinit var binding : DeletedialogBinding
    private lateinit var binding2 : DeletecompletedialogBinding
    private val deleteDlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    fun show(content : String) {
        binding = DeletedialogBinding.inflate(context.layoutInflater)

        // 다이얼로그 테두리 둥글게 만들기
        deleteDlg?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        deleteDlg?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        deleteDlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        deleteDlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        deleteDlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        val params: WindowManager.LayoutParams = this.deleteDlg.window!!.attributes
        params.y = 500
        this.deleteDlg.window!!.attributes = params

        //ok 버튼 동작
        binding.yesBtn.setOnClickListener {
            deleteDlg.dismiss()
            deleteComplete()
        }

        //cancel 버튼 동작
        binding.noBtn.setOnClickListener {
            deleteDlg.dismiss()
        }
        deleteDlg.show()
    }

    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object: MyDialogOKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }

    interface MyDialogOKClickedListener {
        fun onOKClicked(content : String)
    }

    fun deleteComplete() {
        binding2 = DeletecompletedialogBinding.inflate(context.layoutInflater)

        deleteDlg.setContentView(binding2.root)
        deleteDlg.setCancelable(false)
        deleteDlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val params: WindowManager.LayoutParams = this.deleteDlg.window!!.attributes
        params.y = 500
        this.deleteDlg.window!!.attributes = params

        deleteDlg.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        deleteDlg.show()
//      액티비티로 이동(첫화면)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
            (context as Activity).finish()
        }, 3000)
    }

}
