package com.example.clothclassfi.bottom_dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import clothDB.AppDatabase
import com.example.clothclassfi.BottomPlusDialogActivity
import com.example.clothclassfi.databinding.DialogActivityBottomBinding
import com.example.clothclassfi.databinding.DialogActivityTopBinding
import com.example.clothclassfi.top_dialog.TopPlusDialogActivity
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.timer

class BottomDialogDetailActivity: Activity(){
    lateinit var binding: DialogActivityBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogActivityBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRealName.text = intent.getStringExtra("name")
        binding.tvBottomTotal.text = intent.getStringExtra("total")
        binding.tvWaist.text = intent.getStringExtra("waist")
        binding.tvThigh.text = intent.getStringExtra("thigh")
        binding.tvCrotch.text = intent.getStringExtra("crotch")
        binding.tvHem.text = intent.getStringExtra("hem")

        var i = 0
        binding.buttonBottomDelete.setOnClickListener{
            if(i == 1){//기본키(이름)로 찾고 삭제
                val clothDAO = AppDatabase.getDatabase(this).getClothDAO()

                runBlocking {
                    val willRemovedBottomCloth = clothDAO.getBottomByName(intent.getStringExtra("name")!!)
                    clothDAO.deleteBottomCloth(willRemovedBottomCloth)
                }

                finish()
            }

            i++
            Toast.makeText(this, "삭제 버튼을 한번 더 누르면 삭제됩니다.", Toast.LENGTH_SHORT).show()

            var second = 0
            timer(period = 1000){
                second ++
                if(second == 2){
                    i = 0
                    cancel()
                }
            }
        }

        binding.buttonBottomModify.setOnClickListener {
            val intent = Intent(this, BottomPlusDialogActivity::class.java)
            intent.putExtra("fromDetail", "true")
            intent.putExtra("name", binding.tvRealName.text.toString())
            intent.putExtra("total", binding.tvBottomTotal.text.toString())
            intent.putExtra("waist", binding.tvWaist.text.toString())
            intent.putExtra("thigh", binding.tvThigh.text.toString())
            intent.putExtra("crotch", binding.tvCrotch.text.toString())
            intent.putExtra("hem", binding.tvHem.text.toString())

            val clothDAO = AppDatabase.getDatabase(this).getClothDAO()
            var classfi: String = ""
            runBlocking {
                classfi = clothDAO.getClassOfBottomByName(binding.tvRealName.text.toString())
            }

            intent.putExtra("classfi", classfi)

            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2) {
            if (resultCode == RESULT_OK)
                finish()
        }
    }
}