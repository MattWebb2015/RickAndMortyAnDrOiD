package com.example.recyclertest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclertest.models.Character
import kotlin.random.Random

class CharacterListViewModel : ViewModel() {
    private val characters = mutableListOf<Character>()

    private val _thing = MutableLiveData<List<Character>>()
    val thing: LiveData<List<Character>> = _thing

    fun updateLive() {
        if (characters.isEmpty()) {
            characters.addAll(generateCharacters())
        }
        _thing.postValue(characters)
    }

    private fun generateCharacters(): List<Character> {
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
        for (i in 1..80) {
            characters.add(
                Character(
                    name = "${nameMods.random()} ${characterNames.random()}".trimStart(),
                    age = Random.nextInt(0, 100),
                    location = characterLocations.random(),
                    gender = genders.random()
                )
            )
        }
        return characters.toList()
    }
}
