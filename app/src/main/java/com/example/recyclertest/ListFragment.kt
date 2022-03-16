package com.example.recyclertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclertest.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val characters = generateCharacters()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterAdapter = CharacterAdapter(::onCharacterClicked)
        val recyclerview = binding.recyclerview
        characterAdapter.addCharacters(characters)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
        }
    }

    private fun onCharacterClicked(character: Character, position: Int) {
        replaceFragment(CharacterDetailPageFragment.newInstance(character))
    }

    private fun replaceFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.commit {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
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
