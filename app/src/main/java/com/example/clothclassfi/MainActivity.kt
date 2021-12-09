package com.example.clothclassfi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clothclassfi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonTop.setOnClickListener {
            startActivity(Intent(this, SubdivisionActivity::class.java))
        }
        binding.buttonBot.setOnClickListener {
            startActivity(Intent(this, SubdivisionBottom::class.java))
        }
        binding.buttonOuter.setOnClickListener {
            startActivity(Intent(this, SubdivisionOuter::class.java))
        }
    }
}