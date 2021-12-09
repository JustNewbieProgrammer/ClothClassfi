package com.example.clothclassfi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clothclassfi.databinding.ActivitySubdivisionBottomBinding

class SubdivisionBottom : AppCompatActivity() {
    lateinit var binding: ActivitySubdivisionBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubdivisionBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, FinalActivity::class.java)
        intent.putExtra("firstClass", "Bottom")

        binding.buttonShorts.setOnClickListener{
            intent.putExtra("classfi", "반바지")
            startActivity(intent)
        }

        binding.buttonDenimPants.setOnClickListener {
            intent.putExtra("classfi", "데님바지")
            startActivity(intent)
        }

        binding.buttonSlacks.setOnClickListener {
            intent.putExtra("classfi", "슬랙스")
            startActivity(intent)
        }

        binding.buttonCottonPants.setOnClickListener {
            intent.putExtra("classfi", "면바지")
            startActivity(intent)
        }

        binding.buttonTrainingPants.setOnClickListener {
            intent.putExtra("classfi", "트레이닝")
            startActivity(intent)
        }

        binding.buttonLinenPants.setOnClickListener {
            intent.putExtra("classfi", "리넨바지")
            startActivity(intent)
        }

        binding.buttonCorduroy.setOnClickListener {
            intent.putExtra("classfi", "골덴바지")
            startActivity(intent)
        }
    }
}