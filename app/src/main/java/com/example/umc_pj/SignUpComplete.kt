package com.example.umc_pj

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivitySignupcompleteBinding

class SignUpComplete : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySignupcompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignupcompleteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root);


        viewBinding.agreeBtn.isEnabled = true
        viewBinding.agreeBtn.setOnClickListener {
            val intent = Intent(this,DogRegisterActivity::class.java)
            startActivity(intent)
//            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
        }

    }
}