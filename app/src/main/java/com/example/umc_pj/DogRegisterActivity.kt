package com.example.umc_pj

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_pj.databinding.ActivityDogRegisterBinding

import kotlinx.android.synthetic.main.activity_dog_register.*


class DogRegisterActivity : AppCompatActivity() {

    var validEditText: Boolean= false
    var validSpinner1: Boolean= false
    var validSpinner2: Boolean= false
    var validSpinner3: Boolean= false

    lateinit var rv_phone_book: RecyclerView
    lateinit var breedAdapter: BreedAdapter
    lateinit var breed:Array<BreedDTO>

    private lateinit var viewBinding: ActivityDogRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDogRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.dogNameEdtText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                validEditText = editable.isNotEmpty()
                checkValid(validEditText, validSpinner1, validSpinner2, validSpinner3)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let { highlightText(it as Editable) }
            }
        })

        viewBinding.nextPageBtn.setOnClickListener {
            val intent = Intent(this, DogRegisterEndActivity::class.java)
            intent.putExtra("dogname", dog_name_edt_text.text.toString())
            startActivity(intent)
        }


        setupBreedData()
        setupBreedHandler()

        setupGenderData()
        setupGenderHandler()

        setupAgeData()
        setupAgeHandler()
        rv_phone_book = findViewById(R.id.rv_phone_book)

        breed = tempPersons()
        setAdapter()

//        if(validEditText && validSpinner1 && validSpinner2 && validSpinner3) {
//            next_page_btn.isEnabled = true
//            next_page_btn.isClickable = true
//            next_page_btn.setBackgroundResource(R.drawable.start_button)
//        }
    }


    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        rv_phone_book.layoutManager = LinearLayoutManager(this)
        breedAdapter = BreedAdapter(breed, this)
        rv_phone_book.adapter = breedAdapter
    }

    fun tempPersons(): Array<BreedDTO> {
        return arrayOf(
            BreedDTO(1, "kim", "01011111111"),
            BreedDTO(2, "lee", "01022222222"),
            BreedDTO(3, "park", "01033333333"),
            BreedDTO(4, "son", "01044444444"),

        )
    }




    // -- 스피너 높이 조절 코드인데 잘 안되네요 --
        fun limitDropHeight(breed_spinner: Spinner) {
            val popup = Spinner::class.java.getDeclaredField("good")
            popup.isAccessible = true

            val popupWindow = popup.get(breed_spinner) as ListPopupWindow
            popupWindow.height = (50 * resources.displayMetrics.density).toInt()
        }



    // edittext 글자 입력할 때 하이라이트- 마지막 글자만 안바뀜(해결 아직 못함)
    private fun highlightText(text: Editable) {
        viewBinding.dogNameEdtText.text?.let { editable ->
            val spans = editable.getSpans(0, editable.length,
                ForegroundColorSpan::class.java)

            spans.forEach { span ->
                editable.removeSpan(span)
            }
        }

        val endIndex = text.length
        val startIndex = if (endIndex < 1) 0 else (endIndex-1)

        viewBinding.dogNameEdtText.text?.setSpan(
            ForegroundColorSpan(Color.BLACK),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    }

    private fun setupBreedData() {
        val breedSearchView = findViewById<SearchView>(R.id.breed_search)
        breedSearchView.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                breedSearchView.isSelected =  hasFocus
                // isIconified() return true if search view lost focus, this line handle for user press back button case
                // as default when user press back button, the search view lost focus but view doesn't change state (collapse if width = wrap_content)
                breedSearchView.isIconified = !hasFocus
            }

        })

//        val names = arrayOf("ㅎㅇ","ㅎㅇㅇ","ㅎfdㅇㅎㅇㅎㅇ")
//        val list = findViewById<ListView>(R.id.breed)
//        val adapter2: ArrayAdapter<String> = ArrayAdapter(
//            this, android.R.layout.simple_list_item_2,names
//        )
//
//        list.adapter = adapter2
//
//
//        viewBinding.breedSearch.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                viewBinding.breedSearch.clearFocus()
//                if(names.contains(p0))
//                {
//                    adapter2.filter.filter(p0)
//                }else{
//                    Toast.makeText(applicationContext,"Item not found",Toast.LENGTH_SHORT).show()
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter2.filter.filter(newText)
//                return false
//            }
//
//        })

//       breedAdapter.add("견종을 선택해주세요.")
//        breedAdapter.addAll(breedData.toMutableList())

//        viewBinding.breedSpinner.adapter = breedAdapter
//
//        // 스피너 높이 조절 코드- limitDropHeight(breed_spinner)
//
//        breed_spinner.setSelection(0)
//        breed_spinner.dropDownVerticalOffset = dipToPixels(50f).toInt()
    }


    private fun setupBreedHandler() {

//        viewBinding.breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                when(position) {
//                    0 -> {
//                        validSpinner1 = false
//                    }
//                    else -> {
//                        validSpinner1 = true
//                        Log.d("스피너1", "$validSpinner1")
//                    }
//                }
//                checkValid(validEditText, validSpinner1, validSpinner2, validSpinner3)
//
//            }
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                validSpinner1 = false
//            }
//        }
    }

    private fun setupGenderData() {

        val genderData = resources.getStringArray(R.array.spinner_gender)

        val genderAdapter = object : ArrayAdapter<String>(this,R.layout.gender_spinner) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val v = super.getView(position, convertView, parent)
                if (position==0) {
                    (v.findViewById<View>(R.id.tvGenderSpinner) as? TextView)?.text = ""
                    (v.findViewById<View>(R.id.tvGenderSpinner) as? TextView)?.hint = "성별을 선택해주세요."
                }
                return v
            }

            override fun getCount(): Int {
                return super.getCount()
            }

        }
        genderAdapter.add("성별을 선택해주세요.")
        genderAdapter.addAll(genderData.toMutableList())

        viewBinding.dogGenderSpinner.adapter = genderAdapter

        dog_gender_spinner.setSelection(0)
        dog_gender_spinner.dropDownVerticalOffset = dipToPixels(50f).toInt()
    }


    private fun setupGenderHandler() {
        viewBinding.dogGenderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        validSpinner2 = false //이거 무슨 코드죠?
                    }
                    else -> {
                        validSpinner2 = true
                        Log.d("스피너2", "$validSpinner2")

                    }
                }
                checkValid(validEditText, validSpinner1, validSpinner2, validSpinner3)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                validSpinner2 = false
            }
        }
    }


    private fun setupAgeData() {
        val ageData = resources.getStringArray(R.array.spinner_age)
        val ageAdapter = object : ArrayAdapter<String>(this, R.layout.breed_spinner) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val v = super.getView(position, convertView, parent)

                if (position == 0) {
                    (v.findViewById<View>(R.id.tvBreedSpinner) as? TextView)?.text = ""
                    (v.findViewById<View>(R.id.tvBreedSpinner) as? TextView)?.hint = getItem(0)
                }

                return v
            }

            override fun getCount(): Int {
                return super.getCount()
            }
        }

        ageAdapter.add("나이 선택해주세요.")
        ageAdapter.addAll(ageData.toMutableList())

        viewBinding.dogAgeSpinner.adapter = ageAdapter

        dog_age_spinner.setSelection(0)
        dog_age_spinner.dropDownVerticalOffset = dipToPixels(50f).toInt()
    }

    private fun setupAgeHandler() {

        viewBinding.dogAgeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        validSpinner3 = false
                    }
                    else -> {
                        validSpinner3 = true
                        Log.d("스피너3", "$validSpinner3")
                    }
                }
                checkValid(validEditText, validSpinner1, validSpinner2, validSpinner3)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                validSpinner3 = false
            }
        }
    }

    private fun dipToPixels(dipValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue,
            resources.displayMetrics
        )
    }

    private fun checkValid(v1:Boolean, v2:Boolean, v3:Boolean, v4:Boolean){
        Log.d("Valid", (v1 && v2 && v3 && v4).toString())
        if(v1 && v2 && v3 && v4){
            next_page_btn.isEnabled = true
            next_page_btn.isClickable = true
            next_page_btn.setBackgroundResource(R.drawable.start_button)
        } else {
            next_page_btn.isEnabled = false
            next_page_btn.isClickable = false
            next_page_btn.setBackgroundResource(R.drawable.disabled_button)
        }
    }
}



