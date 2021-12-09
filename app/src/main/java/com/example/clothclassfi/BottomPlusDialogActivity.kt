package com.example.clothclassfi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import android.widget.Toast
import clothDB.AppDatabase
import clothDB.BottomCloth
import com.example.clothclassfi.databinding.ActivityBottomPlusDialogBinding
import kotlinx.coroutines.runBlocking

class BottomPlusDialogActivity : Activity() {
    lateinit var binding: ActivityBottomPlusDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityBottomPlusDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromDetail: String? = intent.getStringExtra("fromDetail")
        //detailDialog에서 수정 눌러서 실행된 경우
        if(fromDetail == "true"){
            binding.buttonPlusInBottomDialog.text = "확인"

            binding.etBottomRealName.setText(intent.getStringExtra("name"))
            binding.etBottomTotal.setText(intent.getStringExtra("total"))
            binding.etWaist.setText(intent.getStringExtra("waist"))
            binding.etThigh.setText(intent.getStringExtra("thigh"))
            binding.etCrotch.setText(intent.getStringExtra("crotch"))
            binding.etHem.setText(intent.getStringExtra("hem"))
        }

        binding.buttonPlusInBottomDialog.setOnClickListener{
            if(binding.etBottomRealName.text.toString() == "")
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            else {
                val bottomCloth = BottomCloth(
                        binding.etBottomRealName.text.toString(),
                        binding.etBottomTotal.text.toString(),
                        binding.etWaist.text.toString(),
                        binding.etThigh.text.toString(),
                        binding.etCrotch.text.toString(),
                        binding.etHem.text.toString(),
                        intent.getStringExtra("classfi")!!
                )

                val clothDAO = AppDatabase.getDatabase(this).getClothDAO()

                runBlocking {
                    if (fromDetail == "true") {//detailDialog에서 실행한 경우
                        clothDAO.deleteBottomCloth(clothDAO.getBottomByName(intent.getStringExtra("name")!!))
                        setResult(RESULT_OK, Intent())
                    }
                    clothDAO.insertBottomCloth(bottomCloth)
                }
                finish()
            }
        }

        binding.buttonBottomCancel.setOnClickListener {
            finish()
        }
    }

    override fun onTouchEvent(event : MotionEvent): Boolean {
        //바깥레이어 클릭시 안닫히게
        if(event.action == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}