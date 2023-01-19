package com.example.umc_pj


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_pj.databinding.ActivityInfoBinding
import kotlinx.android.synthetic.main.activity_dog_register.*
import kotlinx.android.synthetic.main.activity_info.*
import java.util.regex.Pattern


class InfoActivity: AppCompatActivity() {

    // 에딧텍스트에 값 4개 다 들어가야 버튼 활성화되는 코드
    var validEditText1: Boolean= false
    var validEditText2: Boolean= false
    var validEditText3: Boolean= false
    var validEditText4: Boolean= false

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 에딧텍스트에 값 4개 다 들어가야 버튼 활성화되는 코드
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                validEditText1 = editable.isNotEmpty()
                checkValid(validEditText1, validEditText2, validEditText3, validEditText4)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                p0?.let { highlightText(it as Editable) }
            }
        })

        binding.editTextTextEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                validEditText2 = editable.isNotEmpty()
                checkValid(validEditText1, validEditText2, validEditText3, validEditText4)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.editTextTextPassword1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                validEditText3 = editable.isNotEmpty()
                checkValid(validEditText1, validEditText2, validEditText3, validEditText4)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                p0?.let { highlightText(it as Editable) }
            }
        })

        binding.editTextTextPassword2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                validEditText4 = editable.isNotEmpty()
                checkValid(validEditText1, validEditText2, validEditText3, validEditText4)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                p0?.let { highlightText(it as Editable) }
            }
        })



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
//    에딧텍스트 활성화 코드
    private fun checkValid(v1:Boolean, v2:Boolean, v3:Boolean, v4:Boolean){
        Log.d("Valid", (v1 && v2 && v3 && v4).toString())
        if(v1 && v2 && v3 && v4){
            next_btn.isEnabled = true
            next_btn.isClickable = true
            next_btn.setBackgroundResource(R.drawable.start_button)
        } else {
            next_btn.isEnabled = false
            next_btn.isClickable = false
            next_btn.setBackgroundResource(R.drawable.disabled_button)
        }
    }
}