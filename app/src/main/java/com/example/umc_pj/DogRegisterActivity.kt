package com.example.umc_pj


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_pj.databinding.ActivityDogRegisterBinding
import kotlinx.android.synthetic.main.activity_dog_register.*


class DogRegisterActivity : AppCompatActivity(),BreedItemClick  {
    var validEditText: Boolean= false
    var validSpinner1: Boolean= false
    var validSpinner2: Boolean= false
    var validSpinner3: Boolean= false

    lateinit var breed_recycleR: RecyclerView
    lateinit var breedAdapter: BreedAdapter
    lateinit var breed:ArrayList<BreedDTO>
    lateinit var BreedSearch: SearchView



    private lateinit var viewBinding: ActivityDogRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDogRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        BreedSearch = findViewById(R.id.breed_search)
        breed_recycleR = findViewById(R.id.rv_phone_book)

        viewBinding.dogNameEdtText.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    breed_recycleR.visibility = View.INVISIBLE
                    breed_search.clearFocus()
                }
            }
            false
        })

        viewBinding.dogNameEdtText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {

                validEditText = editable.isNotEmpty()
                checkValid(validEditText, validSpinner1, validSpinner2, validSpinner3)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                p0?.let { highlightText(it as Editable) }
            }
        })

        viewBinding.nextPageBtn.setOnClickListener {
            val intent = Intent(this, DogRegisterEndActivity::class.java)
//            intent.putExtra("dogname", dog_name_edt_text.text.toString())
            startActivity(intent)
        }
        //배경 클릭시 포커스해제
        viewBinding.background.setOnClickListener {
            breed_recycleR.visibility= View.INVISIBLE
            breed_search.clearFocus()
        }


        BreedSearch.setOnQueryTextListener(searchViewTextListener)
        breed = tempPersons()
        setAdapter()
        setupBreedData()

        setupGenderData()
        setupGenderHandler()

        setupAgeData()
        setupAgeHandler()

    }


    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        breed_recycleR.layoutManager = LinearLayoutManager(this)
        breedAdapter = BreedAdapter(this,breed, this)
        breed_recycleR.adapter = breedAdapter
    }

    fun tempPersons(): ArrayList<BreedDTO> {
        var tempPersons = ArrayList<BreedDTO>()
        tempPersons.add(BreedDTO(""))
        tempPersons.add(BreedDTO("허스키"))
        tempPersons.add(BreedDTO("11111"))
        tempPersons.add(BreedDTO("11111"))
        tempPersons.add(BreedDTO("11111"))
        tempPersons.add(BreedDTO("치와와"))
        tempPersons.add(BreedDTO("시츄"))
        tempPersons.add(BreedDTO("웰시코기"))
        tempPersons.add(BreedDTO("진돗개"))
        tempPersons.add(BreedDTO("풍산개"))
        tempPersons.add(BreedDTO("저팔계"))
        return tempPersons
    }





    // -- 스피너 높이 조절 코드인데 잘 안되네요 --
        fun limitDropHeight(breed_spinner: Spinner) {
            val popup = Spinner::class.java.getDeclaredField("good")
            popup.isAccessible = true

            val popupWindow = popup.get(breed_spinner) as ListPopupWindow
            popupWindow.height = (50 * resources.displayMetrics.density).toInt()
        }





    // edittext 글자 입력할 때 하이라이트- 마지막 글자만 안바뀜(해결 아직 못함)
//    private fun highlightText(text: Editable) {
//        viewBinding.dogNameEdtText.text?.let { editable ->
//            val spans = editable.getSpans(0, editable.length,
//                ForegroundColorSpan::class.java)
//
//            spans.forEach { span ->
//                editable.removeSpan(span)
//            }
//        }
//
//        val endIndex = text.length
//        val startIndex = if (endIndex < 1) 0 else (endIndex-1)
//
//        viewBinding.dogNameEdtText.text?.setSpan(
//            ForegroundColorSpan(Color.BLACK),
//            startIndex,
//            endIndex,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )
//
//    }

    //서치뷰 관련 인터렉션
    private fun setupBreedData() {
        val breedSearchView = findViewById<SearchView>(R.id.breed_search)
        breedSearchView.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                breedSearchView.isSelected =  hasFocus
                breedSearchView.isIconified = !hasFocus
                if(breed_search.isSelected) {
                    breed_recycleR.visibility = View.VISIBLE
                }else if(!breed_search.isSelected){
                    breed_recycleR.visibility = View.INVISIBLE

                }

            }

        })

    }


    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                breedAdapter.filter.filter(s)
                Log.d("gd", "SearchVies Text is changed : $s")
                return false
            }
        }

    private fun setupGenderData() {

        val genderData = resources.getStringArray(R.array.spinner_gender)

        val genderAdapter = object : ArrayAdapter<String>(this,R.layout.gender_spinner) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val v = super.getView(position, convertView, parent)
                if (position == count) {

                    (v.findViewById<View>(R.id.tvGenderSpinner) as TextView).text = ""
                    (v.findViewById<View>(R.id.tvGenderSpinner) as TextView).hint = "성별을 선택해주세요."

                }
                return v
            }

            override fun getCount(): Int {
                return super.getCount() - 1
            }

        }

        genderAdapter.addAll(genderData.toMutableList())
        genderAdapter.add("성별을 선택해주세요.")

        viewBinding.dogGenderSpinner.adapter = genderAdapter

        dog_gender_spinner.setSelection(genderAdapter.count)
        dog_gender_spinner.dropDownVerticalOffset = dipToPixels(50f).toInt()
    }


    private fun setupGenderHandler() {
        viewBinding.dogGenderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when(position) {
                    0 -> {
                        validSpinner2 = true
                        //이거 무슨 코드죠? -> 원래 포지션 0 일 때 성별을 선택해주세요 를 넣어둬서 이걸 선택하면 선택처리 안한다는 코드였습니다.
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

        }

        ageAdapter.add("출생년도를 선택해주세요.")
        ageAdapter.addAll(ageData.toMutableList())

        viewBinding.dogAgeSpinner.adapter = ageAdapter

        dog_age_spinner.setSelection(0)
        dog_age_spinner.dropDownVerticalOffset = dipToPixels(50f).toInt()
    }

    private fun setupAgeHandler() {

        viewBinding.dogAgeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                breed_recycleR.visibility= View.INVISIBLE
                breed_search.clearFocus()
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

    override fun onClick(value: String?) {
        val breedSearchView = findViewById<SearchView>(R.id.breed_search)
        if (!breedAdapter.choose_breed.isEmpty()) {
            breedSearchView.queryHint = breedAdapter.choose_breed
            val editText =
                findViewById<SearchView>(androidx.appcompat.R.id.search_src_text) as EditText
            editText.setHintTextColor(Color.BLACK)
            breedSearchView.clearFocus() // 포커스 초기화
            viewBinding.dogNameEdtText.clearFocus()
            Log.d("네임",breedAdapter.choose_breed)
            breed_recycleR.visibility=View.INVISIBLE
            validSpinner1 = true
        }
    }





    // 화면 클릭하여 키보드 숨기기 및 포커스 제거
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    dog_name_edt_text.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}