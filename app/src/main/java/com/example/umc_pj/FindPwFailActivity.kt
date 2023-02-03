package com.example.umc_pj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_pj.databinding.ActivityFindPwFailBinding
import com.example.umc_pj.databinding.ActivityFindPwSuccessBinding

class FindPwFailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindPwFailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindPwFailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}