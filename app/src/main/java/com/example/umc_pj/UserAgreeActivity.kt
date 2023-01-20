package com.example.umc_pj;
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.CompoundButton
import com.example.umc_pj.databinding.ActivityAgreeBinding
import kotlinx.android.synthetic.main.activity_dog_register.*
import kotlinx.android.synthetic.main.activity_info.*


class UserAgreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgreeBinding
    var isPageOpen : Boolean = false
    lateinit var DownAnim : Animation
    lateinit var UptAnim : Animation



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreeBinding.inflate(layoutInflater)
        setContentView(binding.root);
        //
        UptAnim = AnimationUtils.loadAnimation(this, R.anim.agree_translate_down)
        DownAnim = AnimationUtils.loadAnimation(this,R.anim.agree_translate_up)
        UptAnim.setAnimationListener(SlidingPageAnimationListener())
        DownAnim.setAnimationListener(SlidingPageAnimationListener())
        //
        binding.allAgree.setOnClickListener { onCheckChanged(binding.allAgree) }
        binding.agree1.setOnClickListener { onCheckChanged(binding.agree1) }
        binding.agree2.setOnClickListener { onCheckChanged(binding.agree2) }
        binding.agree3.setOnClickListener { onCheckChanged(binding.agree3) }




        binding.contentbtn1.setOnClickListener {
            if (isPageOpen) {
                binding.content.startAnimation(DownAnim)
            }
            else{
                binding.content.visibility = View.VISIBLE
                binding.content.startAnimation(DownAnim)
            }
        }
        binding.contentbtn2.setOnClickListener {
            if (isPageOpen) {
                binding.content.startAnimation(DownAnim)
            }
            else{
                binding.content.visibility = View.VISIBLE
                binding.content.startAnimation(DownAnim)
            }
        }
        binding.contentbtn3.setOnClickListener {
            if (isPageOpen) {
                binding.content.startAnimation(DownAnim)
            }
            else{
                binding.content.visibility = View.VISIBLE
                binding.content.startAnimation(DownAnim)
            }
        }


        binding.agreeBtn.setOnClickListener {
            if (isPageOpen) {
                binding.content.startAnimation(UptAnim)
            }
            else{
                val intent = Intent(this,SignUpComplete::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
            }
        }
    }

    private inner class SlidingPageAnimationListener : Animation.AnimationListener {

        override fun onAnimationEnd(animation: Animation?) : Unit {
            if(isPageOpen){
                binding.content.visibility=View.INVISIBLE
                isPageOpen = false
                binding.agreeBtn.isEnabled = false
                binding.agreeBtn.text = "다음으로"
                binding.allAgree.isChecked = false
                binding.agree1.isChecked = false
                binding.agree2.isChecked = false
                binding.agree3.isChecked = false
            }
            else{
                isPageOpen = true
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.agreeBtn.isEnabled = true
                    binding.agreeBtn.text = "확인"
                }, 0)
            }
        }
        override fun onAnimationStart(animation: Animation?) {}
        override fun onAnimationRepeat(animation: Animation?) {}
    }



    private fun onCheckChanged(compoundButton: CompoundButton) {
        when(compoundButton.id) {
            R.id.all_agree -> {
                if (binding.allAgree.isChecked) {
                    binding.agree1.isChecked = true
                    binding.agree2.isChecked = true
                    binding.agree3.isChecked = true
                }else {
                    binding.agree1.isChecked = false
                    binding.agree2.isChecked = false
                    binding.agree3.isChecked = false
                }
            }
            else -> {
                binding.allAgree.isChecked = (binding.agree1.isChecked && binding.agree2.isChecked && binding.agree3.isChecked)
            }
        }
        binding.agreeBtn.isEnabled = binding.agree1.isChecked && binding.agree2.isChecked
        if(binding.agree1.isChecked){
            //권한 넣기
        }
        if(binding.agree2.isChecked){
            //권한 넣기
        }
        if(binding.agree3.isChecked){
            //권한 넣기
        }
    }


}