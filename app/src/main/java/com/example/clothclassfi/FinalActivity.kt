package com.example.clothclassfi

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import clothDB.*
import com.example.clothclassfi.databinding.ActivityFinalBinding
import com.example.clothclassfi.databinding.DialogTopAddBinding
import com.example.clothclassfi.outer_dialog.OuterPlusDialogActivity
import com.example.clothclassfi.top_dialog.TopDialogDetailActivity
import com.example.clothclassfi.top_dialog.TopPlusDialogActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class FinalActivity : AppCompatActivity() {
    private val top = mutableListOf<TopCloth>(TopCloth("이름", "총장", "어깨너비", "가슴단면", "소매길이", ""))
    private val bottom = mutableListOf<BottomCloth>(BottomCloth("이름", "총장", "허리단면", "허벅지\n단면", "밑위", "밑단", ""))
    private val outer = mutableListOf<OuterCloth>(OuterCloth("이름", "총장", "어깨너비", "가슴단면", "소매길이", ""))
    private val adapterTop = AdapterTop(top)
    private val adapterBottom = AdapterBottom(bottom)
    private val adapterOuter = AdapterOuter(outer)
    lateinit var binding: ActivityFinalBinding
    lateinit var  clothDAO: ClothDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstClass = intent.getStringExtra("firstClass")

        binding.rvCloth.layoutManager = LinearLayoutManager(this)

        //대분류에 맞는 어댑터 지정
        if(firstClass == "Top"){
            binding.rvCloth.adapter = adapterTop
            adapterTop.notifyDataSetChanged()
        }
        else if(firstClass == "Bottom"){
            binding.rvCloth.adapter = adapterBottom
            adapterBottom.notifyDataSetChanged()
        }
        else if(firstClass == "Outer"){
            binding.rvCloth.adapter = adapterOuter
            adapterOuter.notifyDataSetChanged()
        }
        else{

        }


        //소분류(반팔티, 맨투맨, ...) 정보 받기
        val classfi = intent.getStringExtra("classfi")
        binding.tvClass.text = classfi

        clothDAO = AppDatabase.getDatabase(this).getClothDAO()

        observeCloth(firstClass!!, classfi!!)

        //+버튼(입력버튼) 클릭
        binding.buttonPlus.setOnClickListener{
            val intent: Intent? = when(firstClass){
                "Top" -> Intent(this, TopPlusDialogActivity::class.java)
                "Bottom" -> Intent(this, BottomPlusDialogActivity::class.java)
                "Outer" -> Intent(this, OuterPlusDialogActivity::class.java)
                else -> null
            }
            intent?.putExtra("classfi", classfi)
            startActivity(intent)
        }
    }

    private fun observeCloth(firstClass: String, classfi: String){
        if(firstClass == "Top") {
            val allTopCloth = clothDAO.getClassTopCloth(classfi)
            //변화 생기면 리스트 지우고 열 정보, 요소들 갱신
            allTopCloth.observe(this,
                    Observer<List<TopCloth>> { topCloth ->
                        top.clear()
                        adapterTop.notifyDataSetChanged()

                        top.add(TopCloth("이름", "총장", "어깨너비", "가슴단면", "소매길이", ""))
                        adapterTop.notifyDataSetChanged()

                        top.addAll(topCloth)
                        adapterTop.notifyDataSetChanged()
                    }
            )
        }
        else if(firstClass == "Bottom"){
            val allBottomCloth = clothDAO.getClassBottomCloth(classfi)
            allBottomCloth.observe(this,
                    Observer<List<BottomCloth>> { bottomCloth->
                        bottom.clear()
                        adapterBottom.notifyDataSetChanged()

                        bottom.add(BottomCloth("이름", "총장", "허리단면", "허벅지\n단면", "밑위", "밑단", ""))
                        adapterBottom.notifyDataSetChanged()

                        bottom.addAll(bottomCloth)
                        adapterBottom.notifyDataSetChanged()
                    }
            )
        }
        else if(firstClass == "Outer"){
            val allOuterCloth = clothDAO.getClassOuterCloth(classfi)
            allOuterCloth.observe(this,
                Observer<List<OuterCloth>>{ outerCloth ->
                    outer.clear()
                    adapterOuter.notifyDataSetChanged()

                    outer.add(OuterCloth("이름", "총장", "어깨너비", "가슴단면", "소매길이", ""))
                    adapterOuter.notifyDataSetChanged()

                    outer.addAll(outerCloth)
                    adapterOuter.notifyDataSetChanged()
                }
            )
        }
        else{}
    }
}