package ai.akun.marvel.ui.home.adapter

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.marvel.ui.home.viewholder.CharacterViewHolder
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CharactersAdapter(private val listener: (CharacterDomainEntity) -> Unit) :
    PagingDataAdapter<CharacterDomainEntity, RecyclerView.ViewHolder>(CharacterDiffCallBack()) {

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

    class CharacterDiffCallBack : DiffUtil.ItemCallback<CharacterDomainEntity>() {
        override fun areItemsTheSame(
            oldItem: CharacterDomainEntity,
            newItem: CharacterDomainEntity
        ): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: CharacterDomainEntity,
            newItem: CharacterDomainEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}