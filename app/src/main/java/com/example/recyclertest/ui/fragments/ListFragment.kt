package com.example.recyclertest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclertest.R
import com.example.recyclertest.databinding.FragmentListBinding
import com.example.recyclertest.models.Character
import com.example.recyclertest.ui.fragments.adapters.CharacterAdapter
import com.example.recyclertest.ui.viewmodels.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterAdapter = CharacterAdapter(::onCharacterClicked)
        val recyclerview = binding.recyclerview
        viewModel.updateLive()
        viewModel.thing.observe(viewLifecycleOwner) {
            characterAdapter.addCharacters(it)
        }
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
}
