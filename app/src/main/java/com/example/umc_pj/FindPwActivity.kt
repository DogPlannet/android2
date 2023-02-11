package com.example.umc_pj

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.umc_pj.databinding.ActivityFindPwBinding
import com.example.umc_pj.databinding.ActivityInfoBinding
import kotlinx.android.synthetic.main.activity_dog_register.*
import kotlinx.android.synthetic.main.activity_find_pw.*
import kotlinx.android.synthetic.main.activity_find_pw.next_page_btn
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.fragment_navi_mypage.*
import java.util.regex.Pattern

class FindPwActivity : AppCompatActivity() {

    var validEditText1: Boolean = false
    var validEditText2: Boolean = false

    private lateinit var binding: ActivityFindPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nameEdtText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun afterTextChanged(p0: Editable?) {
                if(name_edt_text.length() > 0){
                    validEditText1 = true
                    if (validEditText1 && validEditText2){
                        next_page_btn.isClickable = true
                        next_page_btn.isEnabled = true
                    }
                } else{
                    validEditText1 = false
                    next_page_btn.isClickable = false
                    next_page_btn.isEnabled = false
                }
            }
        })

        binding.emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun afterTextChanged(p0: Editable?) {
                if(email_edit_text.length() > 0){
                    validEditText2 = true
                    if (validEditText1 && validEditText2){
                        next_page_btn.isClickable = true
                        next_page_btn.isEnabled = true
                    }
                } else{
                    validEditText2 = false
                    next_page_btn.isClickable = false
                    next_page_btn.isEnabled = false
                }
            }
        })

        binding.backButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.nextPageBtn.setOnClickListener {
            var name = binding.nameEdtText.text.toString() // 이름
            var email = binding.emailEditText.text.toString() // 이메일

            if(email.isEmpty()){
                binding.emailLayout.visibility = View.VISIBLE
                next_btn.isEnabled = false
            } else if (!email.contains("@")){
                binding.emailLayout.visibility = View.VISIBLE
            }

            if((email.contains("@")) && (name.isNotEmpty())) {
                // db에 회원정보있으면 find_pw_success로, 없으면 find_pw_fail로
                val intent = Intent(this, FindPwSuccessActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        name_edt_text.clearFocus()
        email_edit_text.clearFocus()
        return true
    }
}