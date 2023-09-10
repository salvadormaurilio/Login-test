package mx.android.buabap.ui.singin

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.android.buabap.R
import mx.android.buabap.core.test.CountingIdlingResourceSingleton
import mx.android.buabap.core.ui.getString
import mx.android.buabap.core.ui.showAlertDialog
import mx.android.buabap.core.ui.showError
import mx.android.buabap.core.ui.showOrHide
import mx.android.buabap.core.ui.snackbar
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.databinding.FragmentSignInBinding
import mx.android.buabap.domain.UserData
import mx.android.buabap.ui.exception.AuthUiException

@AndroidEntryPoint
class SingInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private val singInViewModel by viewModels<SingInViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        lifecycleScope.launch { collectSignInUiState() }
    }

    private fun initUi() = binding.run {
        confirmSignInButton.setOnClickListener {
            CountingIdlingResourceSingleton.increment()
            singInViewModel.signIn(email = emailEditText.getString(), password = passwordEditText.getString())
        }
    }

    private suspend fun collectSignInUiState() = lifecycleScope.launch {
        singInViewModel.signInUiState
            .flowWithLifecycle(lifecycle, CREATED)
            .collect { signUpUiState -> handleSignInUiState(signUpUiState) }
    }

    private fun handleSignInUiState(signUpUiState: SignInUiState?) = signUpUiState?.run {
        when (this) {
            is SignInUiState.Loading -> signInUiStateLoading()
            is SignInUiState.Success -> signInUiStateSuccess(userData)
            is SignInUiState.Error -> signUpInStateError(error)
        }
    }

    private fun signInUiStateLoading() = binding.run {
        signInProgress.showOrHide(true)
        confirmSignInButton.isEnabled = false
    }

    private fun signInUiStateSuccess(userData: UserData) = binding.run {
        signInProgress.showOrHide(false)
        confirmSignInButton.isEnabled = true
        requireContext().showAlertDialog(getString(R.string.success_sign_in, userData.name, userData.email))
        { findNavController().popBackStack() }
        CountingIdlingResourceSingleton.decrement()
    }

    private fun signUpInStateError(error: Throwable) = binding.run {
        signInProgress.showOrHide(false)
        confirmSignInButton.isEnabled = true
        when (error) {
            is AuthException.SignInException -> requireContext().showAlertDialog(getString(R.string.error_sign_in))
            is AuthUiException.EmailException -> emailInputLayout.error = getString(R.string.error_email_invalid)
            is AuthUiException.PasswordException -> passwordInputLayout.error = getString(R.string.error_password_invalid)
            else -> root.snackbar(error.message).showError()
        }
        CountingIdlingResourceSingleton.decrement()
    }
}