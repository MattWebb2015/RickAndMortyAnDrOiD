package com.example.recyclertest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclertest.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), CharacterAdapter.CharacterAdapterListener {
    private var bindingMain: ActivityMainBinding? = null
    private val binding get() = bindingMain!!
    private lateinit var characterAdapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val genders = mutableListOf<String>("Male", "Female", "Pickle", "Squanch", "01010")
        val characterLocations = mutableListOf<String>(
            "Earth",
            "Citadel of Ricks",
            "Testicle Monster Dimension",
            "Random Dimension"
        )
        val nameMods = mutableListOf<String>("Pickle", "", "Smart", "Fused", "Big Arm")
        val characterNames = mutableListOf<String>("Rick", "Morty", "Summer", "Jerry", "Beth")
        val characters = mutableListOf<Character>()
        for (i in 1..20) {
            characters.add(
                Character(
                    name = nameMods.random() + " " + characterNames.random(),
                    age = Random.nextInt(0, 100),
                    location = characterLocations.random(),
                    gender = genders.random()
                )
            )
            characters.add(
                Character(
                    name = nameMods.random() + " " + characterNames.random(),
                    age = Random.nextInt(0, 100),
                    location = characterLocations.random(),
                    gender = genders.random()
                )
            )
            characters.add(
                Character(
                    name = nameMods.random() + " " + characterNames.random(),
                    age = Random.nextInt(0, 100),
                    location = characterLocations.random(),
                    gender = genders.random()
                )
            )
            characters.add(
                Character(
                    name = nameMods.random() + " " + characterNames.random(),
                    age = Random.nextInt(0, 100),
                    location = characterLocations.random(),
                    gender = genders.random()
                )
            )
        }

        val recyclerview = binding.recyclerview
        characterAdapter = CharacterAdapter(characters, this)

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMain = null
    }

    override fun onCharacterClicked(character: Character, position: Int) {
        val intent = Intent(binding.root.context, CharacterDetailPage::class.java)
        intent.putExtra("name", character.name)
        intent.putExtra("age", character.age)
        intent.putExtra("location", character.location)
        intent.putExtra("gender", character.gender)
        startActivity(intent)
    }
}
