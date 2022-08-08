package ai.akun.marvel.ui.home

import ai.akun.characters.domain.model.CharacterDomainEntity
import ai.akun.characters.presentation.CharactersViewModel
import ai.akun.marvel.databinding.FragmentHomeBinding
import ai.akun.marvel.ui.home.adapter.CharactersAdapter
import ai.akun.marvel.ui.home.adapter.CharactersLoadingStateAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCharacters()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initUi()
        startObserving()

        return binding.root
    }

    private fun initUi() {
        adapter = CharactersAdapter(::navigateToCharacter)

        with(binding) {
            recycler.adapter =
                adapter.withLoadStateFooter(footer = CharactersLoadingStateAdapter(adapter))

            retryButton.setOnClickListener { viewModel.getCharacters() }
        }
    }

    private fun startObserving() {

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            lifecycleScope.launch {
                adapter.submitData(characters)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                with(binding) {
                    progress.isVisible = loadStates.refresh is LoadState.Loading
                    layoutError.isVisible = loadStates.refresh is LoadState.Error
                    errorText.text = (loadStates.refresh as? LoadState.Error)?.error?.message
                }
            }
        }
    }

    private fun navigateToCharacter(character: CharacterDomainEntity) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToCharacterDetailFragment(character.id.toString())

        findNavController().navigate(action)
    }
}
