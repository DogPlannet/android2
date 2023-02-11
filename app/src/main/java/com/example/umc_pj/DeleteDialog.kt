package com.example.umc_pj

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.DeletedialogBinding


class DeleteDialog(private val context : AppCompatActivity) {
    private lateinit var listener : MyDialogOKClickedListener
    private lateinit var binding : DeletedialogBinding
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
            Log.d("dd","click ok")
            deleteDlg.dismiss()
            // 액티비티로 이동(첫화면)
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
            (context as Activity).finish()

            // 어플 종료
//            ActivityCompat.finishAffinity(context)
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
}
