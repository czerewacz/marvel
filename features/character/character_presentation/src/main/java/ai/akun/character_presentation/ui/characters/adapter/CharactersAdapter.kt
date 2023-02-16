package ai.akun.character_presentation.ui.characters.adapter

import ai.akun.character_presentation.ui.characters.viewholder.CharacterViewHolder
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CharactersAdapter(private val listener: (ai.akun.character_domain.model.CharacterDomainEntity) -> Unit) :
    PagingDataAdapter<ai.akun.character_domain.model.CharacterDomainEntity, RecyclerView.ViewHolder>(
        CharacterDiffCallBack()
    ) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        character?.let {
            (holder as CharacterViewHolder).apply {
                bindData(it)
                binding.root.setOnClickListener {
                    listener(character)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder.create(parent)
    }

    class CharacterDiffCallBack : DiffUtil.ItemCallback<ai.akun.character_domain.model.CharacterDomainEntity>() {
        override fun areItemsTheSame(
            oldItem: ai.akun.character_domain.model.CharacterDomainEntity,
            newItem: ai.akun.character_domain.model.CharacterDomainEntity
        ): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: ai.akun.character_domain.model.CharacterDomainEntity,
            newItem: ai.akun.character_domain.model.CharacterDomainEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}