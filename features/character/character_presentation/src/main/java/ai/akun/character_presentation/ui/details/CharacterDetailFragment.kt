package ai.akun.character_presentation.ui.details

import ai.akun.character_domain.model.CharacterDomainEntity
import ai.akun.character_presentation.databinding.FragmentCharacterDetailsBinding
import ai.akun.character_presentation.extensions.toLandscape
import ai.akun.core.extensions.gone
import ai.akun.core.extensions.visible
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val viewModel: CharacterDetailViewModel by stateViewModel(state = { args.toBundle() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)


        binding.icNavBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startObserving()

    }

    private fun startObserving() {

        viewModel.characterId.observe(viewLifecycleOwner) { id ->
            viewModel.getCharacter(id)
        }

        viewModel.uiState.observe(viewLifecycleOwner, ::updateUi)

        viewModel.character.observe(viewLifecycleOwner) { character ->
            bindData(character)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            binding.errorLayout.text = error
        }
    }

    private fun updateUi(uiState: CharacterDetailViewModel.UIState) {
        if (uiState == CharacterDetailViewModel.UIState.Loading) binding.spinner.visible()
        else binding.spinner.gone()

        if (uiState == CharacterDetailViewModel.UIState.Success) binding.characterDetailInfo.visible()
        else binding.characterDetailInfo.gone()

        if (uiState == CharacterDetailViewModel.UIState.Error) binding.errorLayout.visible()
        else binding.errorLayout.gone()
    }

    private fun bindData(character: CharacterDomainEntity) {
        with(binding) {
            collapsingLayout.apply {
                setExpandedTitleColor(Color.WHITE)
                setCollapsedTitleTextColor(Color.WHITE)
            }
            characterCover.load(character.thumbnail.toLandscape())
            characterName.title = character.name
            characterDescription.text = character.description
            characterDetailInfo.setCharacter(character)
        }
    }
}


