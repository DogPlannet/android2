package com.example.umc_pj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_pj.databinding.ActivityFindPwSuccessBinding

class FindPwSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindPwSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}