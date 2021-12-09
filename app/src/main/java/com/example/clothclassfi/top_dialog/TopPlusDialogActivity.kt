package com.example.clothclassfi.top_dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import android.widget.Toast
import clothDB.AppDatabase
import clothDB.TopCloth
import com.example.clothclassfi.databinding.DialogTopAddBinding
import kotlinx.coroutines.runBlocking

class TopPlusDialogActivity : Activity(){
    lateinit var binding: DialogTopAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogTopAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromDetail: String? = intent.getStringExtra("fromDetail")
        //detailDialog에서 수정 눌러서 실행된 경우
        if(fromDetail == "true"){
            binding.buttonPlusInTopDialog.text = "확인"

            binding.etTopRealName.setText(intent.getStringExtra("name"))
            binding.etTopTotal.setText(intent.getStringExtra("total"))
            binding.etShoulder.setText(intent.getStringExtra("shoulder"))
            binding.etChest.setText(intent.getStringExtra("chest"))
            binding.etSleeve.setText(intent.getStringExtra("sleeve"))
        }

        binding.buttonPlusInTopDialog.setOnClickListener{
            if(binding.etTopRealName.text.toString() == "")
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            else {
                val clothDAO = AppDatabase.getDatabase(this).getClothDAO()



                val topCloth = TopCloth(
                        binding.etTopRealName.text.toString(),
                        binding.etTopTotal.text.toString(),
                        binding.etShoulder.text.toString(),
                        binding.etChest.text.toString(),
                        binding.etSleeve.text.toString(),
                        intent.getStringExtra("classfi")!!
                )

                runBlocking {
                    if (fromDetail == "true") {//detailDialog에서 실행한 경우
                        clothDAO.deleteTopCloth(clothDAO.getTopByName(intent.getStringExtra("name")!!))
                        setResult(RESULT_OK, Intent())
                    }
                    clothDAO.insertTopCloth(topCloth)
                }


                finish()
            }
        }

        binding.buttonTopCancel.setOnClickListener{
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