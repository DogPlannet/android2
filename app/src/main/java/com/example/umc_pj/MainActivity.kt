package com.example.umc_pj;
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityMainBinding
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var mBackWait:Long = 0
    private val fl: FrameLayout by lazy {
        findViewById(R.id.main_frm)
    }
    // 뒤로가기 2번
    private var backPressedTime : Long = 0
    override fun onBackPressed() {
        Log.d("TAG", "뒤로가기")

        // 2초내 다시 클릭하면 앱 종료
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            finish()
            return
        }
        // 처음 클릭 메시지
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        backPressedTime = System.currentTimeMillis()
    }
    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_search -> {
                //검색 버튼 눌렀을 때
                Toast.makeText(applicationContext, "검색 이벤트 실행", Toast.LENGTH_LONG).show()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
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