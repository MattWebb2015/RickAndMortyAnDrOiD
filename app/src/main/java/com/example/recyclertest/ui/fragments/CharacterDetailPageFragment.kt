package com.example.recyclertest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.recyclertest.R
import com.example.recyclertest.databinding.FragmentCharacterDetailPageBinding
import com.example.recyclertest.models.Character

class CharacterDetailPageFragment : Fragment(R.layout.fragment_character_detail_page) {
    private lateinit var binding: FragmentCharacterDetailPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.characterName.text =
                getString(R.string.name, requireArguments().getString(BUNDLE_NAME))
            binding.characterAge.text = getString(
                R.string.age,
                requireArguments().getString(
                    BUNDLE_AGE
                )
            )
            binding.characterLocation.text =
                getString(R.string.location, requireArguments().getString(BUNDLE_LOCATION))
            binding.characterGender.text =
                getString(R.string.gender, requireArguments().getString(BUNDLE_GENDER))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.commit {
            replace(R.id.fragmentContainer, fragment)
        }
    }
    companion object {
        const val BUNDLE_NAME = "name"
        const val BUNDLE_AGE = "age"
        const val BUNDLE_LOCATION = "location"
        const val BUNDLE_GENDER = "gender"
        fun newInstance(character: Character): CharacterDetailPageFragment {
            val clickedCharacter = Bundle()
            clickedCharacter.putString(BUNDLE_NAME, character.name)
            clickedCharacter.putString(BUNDLE_AGE, character.id.toString())
            clickedCharacter.putString(BUNDLE_LOCATION, character.location.name)
            clickedCharacter.putString(BUNDLE_GENDER, character.gender)
//            setFragmentResult("characterKey", clickedCharacter)
            val characterDetailPageFragment = CharacterDetailPageFragment()
            characterDetailPageFragment.arguments = clickedCharacter
            return characterDetailPageFragment
        }
    }
}
