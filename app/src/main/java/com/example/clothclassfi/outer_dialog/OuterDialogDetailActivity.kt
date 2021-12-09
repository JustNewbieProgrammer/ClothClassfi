package com.example.clothclassfi.outer_dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import clothDB.AppDatabase
import com.example.clothclassfi.databinding.DialogActivityTopBinding
import com.example.clothclassfi.top_dialog.TopPlusDialogActivity
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.timer

class OuterDialogDetailActivity: Activity() {
    lateinit var binding: DialogActivityTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogActivityTopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRealName.text = intent.getStringExtra("name")
        binding.tvTopTotal.text = intent.getStringExtra("total")
        binding.tvShoulder.text = intent.getStringExtra("shoulder")
        binding.tvChest.text = intent.getStringExtra("chest")
        binding.tvSleeve.text = intent.getStringExtra("sleeve")

        var i = 0
        binding.buttonDelete.setOnClickListener{
            if(i == 1){//기본키(이름)로 찾고 삭제
                val clothDAO = AppDatabase.getDatabase(this).getClothDAO()

                runBlocking {
                    val willRemovedOuterCloth = clothDAO.getOuterByName(intent.getStringExtra("name")!!)
                    clothDAO.deleteOuterCloth(willRemovedOuterCloth)
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

        binding.buttonModify.setOnClickListener {
            val intent = Intent(this, OuterPlusDialogActivity::class.java)
            intent.putExtra("fromDetail", "true")
            intent.putExtra("name", binding.tvRealName.text.toString())
            intent.putExtra("total", binding.tvTopTotal.text.toString())
            intent.putExtra("shoulder", binding.tvShoulder.text.toString())
            intent.putExtra("chest", binding.tvChest.text.toString())
            intent.putExtra("sleeve", binding.tvSleeve.text.toString())

            val clothDAO = AppDatabase.getDatabase(this).getClothDAO()
            var classfi: String = ""
            runBlocking {
                classfi = clothDAO.getClassOfOuterByName(binding.tvRealName.text.toString())
            }

            intent.putExtra("classfi", classfi)

            startActivityForResult(intent, 3)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 3) {
            if (resultCode == RESULT_OK)
                finish()
        }
    }
}