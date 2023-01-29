package com.example.umc_pj

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.background.setOnClickListener{
//            et_id.clearFocus()
//            et_pw.clearFocus()
//        }


        binding.loginBtn.setOnClickListener {

            var id = binding.etId.text.toString() // 아이디
            var pw = binding.etPw.text.toString() // 비번

            if(id.isNotEmpty() && id.isNotBlank() && pw.isNotEmpty() && pw.isNotBlank()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                binding.warningText.visibility = View.VISIBLE
            }

        }

        binding.infoBtn.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        binding.findBtn.setOnClickListener {
            val intent = Intent(this, FindActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        et_id.clearFocus()
        et_pw.clearFocus()
        binding.warningText.visibility = View.INVISIBLE
        return true
    }



}