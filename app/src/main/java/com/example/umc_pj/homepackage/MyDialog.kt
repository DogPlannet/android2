package com.example.umc_pj.homepackage

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.umc_pj.R
import com.example.umc_pj.databinding.CustomdialogBinding
import com.example.umc_pj.databinding.CustomdialogWalkFailBinding
import com.example.umc_pj.databinding.CustomdialogWalkResultBinding
import com.example.umc_pj.databinding.CustomdialogWalkStartBinding

class MyDialog(private val context : AppCompatActivity) {
    private lateinit var listener : MyDialogOKClickedListener
    private lateinit var binding : CustomdialogBinding
    private lateinit var binding2 : CustomdialogWalkStartBinding
    private lateinit var binding3 : CustomdialogWalkFailBinding
    private lateinit var binding4 : CustomdialogWalkResultBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    fun show() {
        binding = CustomdialogBinding.inflate(context.layoutInflater)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val params: WindowManager.LayoutParams = this.dlg.window!!.attributes
        params.y = 800
        this.dlg.window!!.attributes = params
        dlg.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        //ok 버튼 동작
        binding.yes.setOnClickListener {
            dlg.dismiss()
            walkstart()
        }
        //cancel 버튼 동작
        binding.no.setOnClickListener {
            dlg.dismiss()
            walkresult()

        }

        dlg.show()
    }

    fun setOnOKClickedListener(listener: (String) -> Unit) {
        this.listener = object: MyDialogOKClickedListener {
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }
    fun walkstart(){
        binding2 = CustomdialogWalkStartBinding.inflate(context.layoutInflater)
//            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding2.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        Glide.with(context).load(R.drawable.movedog).override(200, 200).into(binding2.moveGif)
//이러면 멈춤        Glide.with(context).load(R.drawable.walk_box).override(200, 200).into(binding2.moveGif)

        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val params: WindowManager.LayoutParams = this.dlg.window!!.attributes
        params.y = 650
        this.dlg.window!!.attributes = params
        dlg.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dlg.show()
    }

    fun walkfail(){
        binding3 = CustomdialogWalkFailBinding.inflate(context.layoutInflater)
//            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding3.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val params: WindowManager.LayoutParams = this.dlg.window!!.attributes
        params.y = 800
        this.dlg.window!!.attributes = params
        dlg.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dlg.show()
        binding3.walkResultClose.setOnClickListener {
            dlg.dismiss()
        }
    }
    fun walkresult(){
        binding4 = CustomdialogWalkResultBinding.inflate(context.layoutInflater)
        dlg.setContentView(binding4.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val params: WindowManager.LayoutParams = this.dlg.window!!.attributes
        params.y = 100
        this.dlg.window!!.attributes = params
        dlg.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dlg.show()
        binding4.walkResultClose.setOnClickListener {
            dlg.dismiss()
        }
    }



    interface MyDialogOKClickedListener {
        fun onOKClicked(content : String)
    }
}