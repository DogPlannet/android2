package com.example.umc_pj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityFindBinding

class FindActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}