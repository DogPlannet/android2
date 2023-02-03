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
import androidx.core.app.ActivityCompat
import com.example.umc_pj.databinding.LogoutdialogBinding
import kotlinx.coroutines.delay


class LogoutDialog(private val context : AppCompatActivity) {
    private lateinit var listener : MyDialogOKClickedListener
    private lateinit var binding : LogoutdialogBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

//    private var customDialog: CustomDialog? = null
//
//    init {
//        this.customDialog = customDialog
//    }


    fun show(content : String) {
        binding = LogoutdialogBinding.inflate(context.layoutInflater)

        // 다이얼로그 테두리 둥글게 만들기
        dlg?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        val params: WindowManager.LayoutParams = this.dlg.window!!.attributes
        params.y = 500
        this.dlg.window!!.attributes = params

        //ok 버튼 동작
        binding.yesBtn.setOnClickListener {
            Log.d("dd","click ok")
            dlg.dismiss()

            // 액티비티로 이동(첫화면)
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
            (context as Activity).finish()

            // 어플 종료
//            ActivityCompat.finishAffinity(context)
        }

        //cancel 버튼 동작
        binding.noBtn.setOnClickListener {
            dlg.dismiss()
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

    interface MyDialogOKClickedListener {
        fun onOKClicked(content : String)
    }
}

//
//class LogoutDialog(): DialogFragment() {
//
//    private var _binding: LogoutdialogBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = LogoutdialogBinding.inflate(inflater, container, false)
//        val view = binding.root
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
//
//        initDialog()
//        return view
//    }
//
//    fun initDialog() {
//
//        binding.yesBtn.setOnClickListener {
//            buttonClickListener.onButton1Clicked()
//            dismiss()
//        }
//
//        binding.noBtn.setOnClickListener {
//            buttonClickListener.onButton2Clicked()
//            dismiss()
//        }
//
//    }
//
//    interface OnButtonClickListener {
//        fun onButton1Clicked()
//        fun onButton2Clicked()
//    }
//
//    override fun onStart() {
//        super.onStart();
//        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()
//        lp.copyFrom(dialog!!.window!!.attributes)
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        val window: Window = dialog!!.window!!
//        window.attributes = lp
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    // 클릭 이벤트 설정
//    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
//        this.buttonClickListener = buttonClickListener
//    }
//
//    // 클릭 이벤트 실행
//    private lateinit var buttonClickListener: OnButtonClickListener
//}
