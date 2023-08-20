package ai.akun.character_presentation.ui.characters


import ai.akun.character_domain.model.CharacterDomainEntity
import ai.akun.character_presentation.databinding.FragmentCharactersBinding
import ai.akun.character_presentation.ui.characters.adapter.CharactersAdapter
import ai.akun.character_presentation.ui.characters.adapter.CharactersLoadingStateAdapter
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

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
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
        binding = FragmentCharactersBinding.inflate(inflater, container, false)

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
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character.id.toString())

        findNavController().navigate(action)
    }
}
