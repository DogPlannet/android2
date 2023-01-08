package com.example.umc_pj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivitySignupcompleteBinding

class SignUpComplete : AppCompatActivity() {
    private lateinit var binding: ActivitySignupcompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupcompleteBinding.inflate(layoutInflater)
        setContentView(binding.root);


        binding.agreeBtn.isEnabled = true
        binding.agreeBtn.setOnClickListener {
            val intent = Intent(this,UserAgreeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

    }
}