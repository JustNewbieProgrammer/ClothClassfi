package com.example.clothclassfi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clothclassfi.databinding.ActivitySubdivisionOuterBinding

class SubdivisionOuter : AppCompatActivity() {
    lateinit var binding: ActivitySubdivisionOuterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubdivisionOuterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, FinalActivity::class.java)
        intent.putExtra("firstClass", "Outer")

        binding.buttonWinterCoat.setOnClickListener{
            intent.putExtra("classfi", "겨울코트")
            startActivity(intent);
        }
        binding.buttonSpringFallCoat.setOnClickListener {
            intent.putExtra("classfi", "환절기코트")
            startActivity(intent);
        }
        binding.buttonDenimTruckerJacket.setOnClickListener {
            intent.putExtra("classfi", "데님/트러커자켓")
            startActivity(intent);
        }
        binding.buttonCardigan.setOnClickListener {
            intent.putExtra("classfi", "가디건")
            startActivity(intent);
        }
        binding.buttonShortPadding.setOnClickListener {
            intent.putExtra("classfi", "숏패딩")
            startActivity(intent);
        }
        binding.buttonFleece.setOnClickListener {
            intent.putExtra("classfi", "후리스")
            startActivity(intent)
        }
        binding.buttonMinimalJacket.setOnClickListener {
            intent.putExtra("classfi", "미니멀자켓")
            startActivity(intent);
        }
        binding.buttonZipUpHoodie.setOnClickListener {
            intent.putExtra("classfi", "후드집업")
            startActivity(intent);
        }
        binding.buttonMa1.setOnClickListener {
            intent.putExtra("classfi", "MA-1")
            startActivity(intent);
        }
    }
}