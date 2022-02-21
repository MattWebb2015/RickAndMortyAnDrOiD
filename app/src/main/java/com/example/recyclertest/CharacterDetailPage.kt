package com.example.recyclertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclertest.databinding.ActivityCharacterDetailPageBinding

class CharacterDetailPage : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterDetailPageBinding
    private var selectedCharacter: Character? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacterIntent()
    }

    private fun getCharacterIntent() {
        if (intent.hasExtra("name") && intent.hasExtra("age") && intent.hasExtra("location") && intent.hasExtra(
                "gender"
            )
        ) {
            binding.characterName.text = intent.getStringExtra("name")
            binding.characterAge.text = intent.getIntExtra("age", 0).toString()
            binding.characterLocation.text = intent.getStringExtra("location")
            binding.characterGender.text = intent.getStringExtra("gender")
        }
    }
}
