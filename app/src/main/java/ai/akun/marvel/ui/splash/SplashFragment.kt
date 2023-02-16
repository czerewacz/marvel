package ai.akun.marvel.ui.splash

import ai.akun.marvel.databinding.FragmentSplashBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)


        lifecycleScope.launch {
            delay(2000)
            navigateToHome()
        }

        return binding.root
    }

    private fun navigateToHome() {
        val action = SplashFragmentDirections.actionFragmentToCharacterGraph()
        findNavController().navigate(action)
    }
}