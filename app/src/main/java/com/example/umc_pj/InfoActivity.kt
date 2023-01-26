package com.example.umc_pj

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityInfoBinding
import kotlinx.android.synthetic.main.activity_info.*
import java.util.regex.Pattern

class InfoActivity: AppCompatActivity() {

    // 에딧텍스트에 값 4개 다 들어가야 버튼 활성화되는 코드
    var validEditText1: Boolean = false
    var validEditText2: Boolean = false
    var validEditText3: Boolean = false
    var validEditText4: Boolean = false

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun afterTextChanged(p0: Editable?) {
                if(et_name.length() > 0){
                    validEditText1 = true
                    if (validEditText1 && validEditText2 && validEditText3 && validEditText4){
                        next_btn.isClickable = true
                        next_btn.isEnabled = true
                    }
                } else{
                    validEditText1 = false
                    next_btn.isClickable = false
                    next_btn.isEnabled = false
                }
            }
        })

        binding.editTextTextEmailAddress.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun afterTextChanged(p0: Editable?) {
                if(editTextTextEmailAddress.length() > 0){
                    validEditText2 = true
                    if (validEditText1 && validEditText2 && validEditText3 && validEditText4){
                        next_btn.isClickable = true
                        next_btn.isEnabled = true
                    }
                } else{
                    validEditText2 = false
                    next_btn.isClickable = false
                    next_btn.isEnabled = false
                }
            }
        })

        binding.editTextTextPassword1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun afterTextChanged(p0: Editable?) {
                if(editTextTextPassword1.length() > 0){
                    validEditText3 = true
                    if (validEditText1 && validEditText2 && validEditText3 && validEditText4){
                        next_btn.isClickable = true
                        next_btn.isEnabled = true
                    }
                } else{
                    validEditText3 = false
                    next_btn.isClickable = false
                    next_btn.isEnabled = false
                }
            }
        })

        binding.editTextTextPassword2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun afterTextChanged(p0: Editable?) {
                if(editTextTextPassword2.length() > 0){
                    validEditText4 = true
                    if (validEditText1 && validEditText2 && validEditText3 && validEditText4){
                        next_btn.isClickable = true
                        next_btn.isEnabled = true
                    }
                } else{
                    validEditText4 = false
                    next_btn.isClickable = false
                    next_btn.isEnabled = false
                }
            }
        })



            binding.nextBtn.setOnClickListener {
                var name = binding.etName.text.toString() // 이름
                var email = binding.editTextTextEmailAddress.text.toString() // 이메일
                var pw1 = binding.editTextTextPassword1.text.toString() // 비밀번호
                var pw2 = binding.editTextTextPassword2.text.toString() // 비밀번호 확인

                if(email.isEmpty()){
                    binding.emailLayout.visibility = View.VISIBLE
                    next_btn.isEnabled = false
                } else if (!email.contains("@")){
                    binding.emailLayout.visibility = View.VISIBLE
                }

                if(pw1.isEmpty()){
                    binding.pw1Layout.visibility = View.VISIBLE
                    next_btn.isEnabled = false
                } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$", pw1)) {
                    binding.pw1Layout.visibility = View.VISIBLE
                }

                if(pw2.isEmpty()){
                    binding.pw2Layout.visibility = View.VISIBLE
                    next_btn.isEnabled = false
                } else if (pw1 != pw2){
                    binding.pw2Layout.visibility = View.VISIBLE
                }

                if(Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$", pw1) && (pw1 == pw2) && (email.contains("@")) && (name.isNotEmpty())){
                    val intent = Intent(this, UserAgreeActivity::class.java)
                    startActivity(intent)
                }
            }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        binding.emailLayout.visibility = View.INVISIBLE
        binding.pw1Layout.visibility = View.INVISIBLE
        binding.pw2Layout.visibility = View.INVISIBLE
        return true
    }

}