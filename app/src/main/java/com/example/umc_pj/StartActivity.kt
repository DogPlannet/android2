package com.example.umc_pj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_pj.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityStartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.startBtn.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        viewBinding.loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}