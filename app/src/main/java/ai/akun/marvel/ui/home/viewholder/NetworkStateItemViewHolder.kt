package ai.akun.marvel.ui.home.viewholder

import ai.akun.marvel.databinding.LayoutLoadingStateBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

class NetworkStateItemViewHolder(
    private val binding: LayoutLoadingStateBinding,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retryCallback() }
    }

    fun bindData(loadState: LoadState) {
        with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible =
                !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateItemViewHolder {
            val binding = LayoutLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return NetworkStateItemViewHolder(binding, retryCallback)
        }
    }
}