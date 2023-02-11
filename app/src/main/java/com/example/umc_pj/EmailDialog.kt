package com.example.umc_pj
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.umc_pj.databinding.EmaildialogBinding
import com.example.umc_pj.databinding.LogoutcompletedialogBinding
import com.example.umc_pj.databinding.LogoutdialogBinding


class EmailDialog(private val context : AppCompatActivity) {
    private lateinit var listener : MyDialogOKClickedListener
    private lateinit var binding : EmaildialogBinding
    private var EmailDlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    fun show(content : String) {
        binding = EmaildialogBinding.inflate(context.layoutInflater)

        // 다이얼로그 테두리 둥글게 만들기
        EmailDlg?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        EmailDlg?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        EmailDlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        EmailDlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        EmailDlg.setCancelable(true)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        val params: WindowManager.LayoutParams = this.EmailDlg.window!!.attributes
        params.y = 500
        this.EmailDlg.window!!.attributes = params

        //ok 버튼 동작
        binding.yesBtn.setOnClickListener {
            EmailDlg.dismiss()

            // 이메일 보내기
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            val address = arrayOf("dogPlannet@dogplannet.com")
            intent.putExtra(Intent.EXTRA_EMAIL, address)
            intent.putExtra(Intent.EXTRA_SUBJECT, "멍플래닛 문의")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            context.startActivity(intent)

            // 액티비티로 이동(첫화면)
//            val intent = Intent(context, SendEmailActivity::class.java)
//            context.startActivity(intent)
//            (context as Activity).finish()

            // 어플 종료
//            ActivityCompat.finishAffinity(context)
        }

        //cancel 버튼 동작
        binding.noBtn.setOnClickListener {
            EmailDlg.dismiss()
        }
        EmailDlg.show()
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

//lateinit var mainActivity: MainActivity
//
//private fun onClick(view: View?) {
//    val logoutDlg = LogoutDialog(mainActivity)
//    Log.d("gdsa","asdgdsg")
//    logoutDlg.setOnOKClickedListener{ content ->
//        binding.yesBtn.text = content
//    }
//    logoutDlg.show("메인의 내용을 변경할까요?")
//}
