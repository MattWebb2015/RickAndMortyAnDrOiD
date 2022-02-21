package com.example.recyclertest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclertest.databinding.CharacterViewBinding

class CharacterAdapter(
    private val characters: List<Character>,
    private val characterAdapterListener: CharacterAdapterListener
) :
    RecyclerView.Adapter<CharacterViewHolder>(),
    CharacterViewHolder.CharacterListener {

    interface CharacterAdapterListener {
        fun onCharacterClicked(character: Character, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holderCharacter: CharacterViewHolder, position: Int) {
        with(holderCharacter) {
            with((characters[position])) {
                binding.characterName.text = this.name
                binding.characterAge.text = this.age.toString()
                binding.root.setOnClickListener {
                    this@CharacterAdapter.onCharacterClicked(absoluteAdapterPosition)
                }
            }
        }
    }

    override fun getItemCount() = characters.size
    override fun onCharacterClicked(position: Int) {
        characterAdapterListener.onCharacterClicked(characters[position], position)
    }
}

class CharacterViewHolder(
    val binding: CharacterViewBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    interface CharacterListener {
        fun onCharacterClicked(position: Int)
    }
}
