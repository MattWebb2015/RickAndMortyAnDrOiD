package com.example.recyclertest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclertest.databinding.CharacterViewBinding

class CharacterAdapter(

    val onRickClickListener: (character: Character, position: Int) -> Unit
) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    private lateinit var characters: List<Character>

    fun addCharacters(updatedCharacters: List<Character>) {
        characters = updatedCharacters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding) {
            onRickClickListener(characters[it], it)
        }
    }

    override fun onBindViewHolder(holderCharacter: CharacterViewHolder, position: Int) {
        with(holderCharacter) {
            with((characters[position])) {
                bindCharacter(this)
            }
        }
    }

    override fun getItemCount() = characters.size
}

class CharacterViewHolder(
    val binding: CharacterViewBinding,
    val onCharacterClickListener: (position: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onCharacterClickListener(absoluteAdapterPosition)
        }
    }
    fun bindCharacter(character: Character) {
        binding.characterName.text = character.name
        binding.characterAge.text = character.age.toString()
    }
}
