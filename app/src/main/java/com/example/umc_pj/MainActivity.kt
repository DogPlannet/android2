package com.example.umc_pj;
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityMainBinding
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val fl: FrameLayout by lazy {
        findViewById(R.id.main_frm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val main_bnv = findViewById<BottomNavigationView>(R.id.main_bnv)

        //supportFragmentManager.beginTransaction().add(R.id.fl_con, NaviHomeFragment()).commit()

        main_bnv.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.navigation_home -> {
                        main_bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.purple_200)
                        main_bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.purple_200)
                        NaviHomeFragment()
                        // Respond to navigation item 1 click
                    }
                    R.id.navigation_community -> {
                        main_bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.purple_200)
                        main_bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.purple_200)
                        NaviCommunityFragment()
                        // Respond to navigation item 2 click
                    }
                    R.id.navigation_notice -> {
                        main_bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.purple_200)
                        main_bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.purple_200)
                        NaviNoticeFragment()
                        // Respond to navigation item 3 click
                    }
                    else -> {
                        main_bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.purple_200)
                        main_bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.purple_200)
                        NaviMypageFragment()
                    }
                }
            )
            true
        }
        main_bnv.selectedItemId = R.id.navigation_home
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frm, fragment)
            .commit()
    }
}