package ai.akun.marvel.ui.home.adapter

import ai.akun.marvel.ui.home.viewholder.NetworkStateItemViewHolder
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharactersLoadingStateAdapter(
    private val adapter: CharactersAdapter
) : LoadStateAdapter<NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder.create(parent) { adapter.retry() }
    }

}