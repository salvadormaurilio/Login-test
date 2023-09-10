package mx.android.buabap.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import mx.android.buabap.R
import mx.android.buabap.databinding.FragmentAuthBinding
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        lifecycleScope.launch { collectNavigationToAuthAction() }
    }

    private fun initUi() = binding.run {
        signUpButton.setOnClickListener { authViewModel.navigateToSingUp() }
        signInButton.setOnClickListener { authViewModel.navigateToSingIn() }
    }

    private suspend fun collectNavigationToAuthAction() {
        authViewModel.navigateToAuthAction
            .flowWithLifecycle(lifecycle, CREATED)
            .collect { authAction -> openAuthAction(authAction) }
    }

    private fun openAuthAction(authAction: AuthAction?) = authAction?.run {
        when (this) {
            is SignUp -> openSingUpFragment()
            is SignIn -> openSingInFragment()
        }
    }

    private fun openSingUpFragment() {
        try {
            findNavController()
                .navigate(R.id.action_authFragment_to_singUpFragment)
        } catch (e: Exception) {
            // No need to do anything.
        }
    }

    private fun openSingInFragment() {
        try {
            findNavController()
                .navigate(R.id.action_authFragment_to_singInFragment)
        } catch (e: Exception) {
            // No need to do anything.
        }
    }
}