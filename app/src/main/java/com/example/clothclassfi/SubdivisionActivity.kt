package com.example.clothclassfi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clothclassfi.databinding.ActivitySubdivisionBinding

class SubdivisionActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubdivisionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubdivisionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, FinalActivity::class.java)
        intent.putExtra("firstClass", "Top")

        binding.buttonBanT.setOnClickListener{
            intent.putExtra("classfi", "반팔티")
            startActivity(intent)
        }

        binding.buttonMTM.setOnClickListener {
            intent.putExtra("classfi", "맨투맨")
            startActivity(intent)
        }

        binding.buttonKnit.setOnClickListener {
            intent.putExtra("classfi", "니트")
            startActivity(intent)
        }

        binding.buttonHoodie.setOnClickListener {
            intent.putExtra("classfi", "후드티")
            startActivity(intent)
        }

        binding.buttonCollarT.setOnClickListener {
            intent.putExtra("classfi", "카라티")
            startActivity(intent)
        }

        binding.buttonLongSleeveT.setOnClickListener {
            intent.putExtra("classfi", "긴팔티")
            startActivity(intent)
        }

        binding.buttonShirts.setOnClickListener {
            intent.putExtra("classfi", "셔츠")
            startActivity(intent)
        }

        binding.buttonBanShirts.setOnClickListener {
            intent.putExtra("classfi", "반팔셔츠")
            startActivity(intent)
        }
    }
}