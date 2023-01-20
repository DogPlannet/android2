package com.example.umc_pj


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityInfoBinding
import java.util.regex.Pattern

class InfoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.nextBtn.setOnClickListener {
                var email = binding.editTextTextEmailAddress.text.toString() // 이메일
                var pw1 = binding.editTextTextPassword1.text.toString() // 비밀번호
                var pw2 = binding.editTextTextPassword2.text.toString() // 비밀번호 확인

                if(email.isEmpty()){
                    binding.emailLayout.visibility = View.VISIBLE
                } else if (!email.contains("@")){
                    binding.emailLayout.visibility = View.VISIBLE
                }

                if(pw1.isEmpty()){
                    binding.pw1Layout.visibility = View.VISIBLE
                } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$", pw1)) {
                    binding.pw1Layout.visibility = View.VISIBLE
                }


                if(pw2.isEmpty()){
                    binding.pw2Layout.visibility = View.VISIBLE
                } else if (pw1 != pw2){
                    binding.pw2Layout.visibility = View.VISIBLE
                }

                else{
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