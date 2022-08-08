package ai.akun.marvel.ui.home.viewholder

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.marvel.databinding.ItemCharacterBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

class CharacterViewHolder(val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(character: CharacterDomainEntity) {
        with(binding) {
            characterCover.load(character.thumbnail)
            characterName.text = character.name
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CharacterViewHolder(binding)
        }
    }
}