package com.example.umc_pj.homepackage

import android.app.Dialog
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.CustomdialogBinding

class MyDialog(private val context : AppCompatActivity) {
    private lateinit var listener : MyDialogOKClickedListener
    private lateinit var binding : CustomdialogBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    fun show(content : String) {
        binding = CustomdialogBinding.inflate(context.layoutInflater)

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함


//        //ok 버튼 동작
//        binding.ok.setOnClickListener {
//
//            //TODO: 부모 액티비티로 내용을 돌려주기 위해 작성할 코드
//
//            dlg.dismiss()
//        }
//
//        //cancel 버튼 동작
//        binding.cancel.setOnClickListener {
//            dlg.dismiss()
//        }

        dlg.show()
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