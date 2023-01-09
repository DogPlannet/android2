package com.example.umc_pj


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityInfoBinding

class InfoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var name = binding.etName.text.toString()
//        var email = binding.editTextTextEmailAddress.text.toString()
//        var password1 = binding.editTextTextPassword1.text.toString()
//        var password2 = binding.editTextTextPassword2.text.toString()

//        if (name != "" && email != "" && password1 != "" && password2 != ""){
//            binding.nextBtn.setBackgroundColor(Color.parseColor("#574444"))
//            binding.nextBtn.setTextColor(Color.parseColor("#e2e2e2"))

            binding.nextBtn.setOnClickListener {
                val intent = Intent(this, UserAgreeActivity::class.java)
                startActivity(intent)
            }
//        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

}