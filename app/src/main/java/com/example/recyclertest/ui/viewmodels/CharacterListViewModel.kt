package com.example.recyclertest.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclertest.models.Character
import com.example.recyclertest.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repo: CharactersRepository
) : ViewModel() {
    val characterids: String = generateIdList().toString().replace(" ", "")
    private val characters = mutableListOf<Character>()
    private val _thing = MutableLiveData<List<Character>>()
    val thing: LiveData<List<Character>> = _thing

    fun updateLive() = runBlocking {
        launch {
            if (characters.isEmpty()) {
                characters.addAll(repo.getCharacters(characterids).body()!!.toSet())
            }
            _thing.postValue(characters)
        }
    }
    private fun generateIdList(): List<Int> {
        val listcharacterids = mutableListOf<Int>()
        for (i in 1..80) {
            listcharacterids.add(Random.nextInt(1, 826))
        }
        return listcharacterids.toList()
    }
}
