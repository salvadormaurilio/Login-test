package mx.android.buabap.ui.singin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.android.buabap.R
import mx.android.buabap.core.ui.getString
import mx.android.buabap.core.ui.showError
import mx.android.buabap.core.ui.showOrHide
import mx.android.buabap.core.ui.showSuccess
import mx.android.buabap.core.ui.snackbar
import mx.android.buabap.data.datasource.exception.SignInException
import mx.android.buabap.databinding.ActivitySignInBinding
import mx.android.buabap.domain.UserData
import mx.android.buabap.ui.exception.AuthUiException

@AndroidEntryPoint
class SingInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val singInViewModel by viewModels<SingInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        lifecycleScope.launch { collectSignInUiState() }
    }

    private fun initUi() = binding.run {
        signInButton.setOnClickListener {
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
            is SignInUiState.Loading -> signUpUiStateLoading()
            is SignInUiState.Success -> signUpUiStateSuccess(userData)
            is SignInUiState.Error -> signUpUiStateError(error)
        }
    }

    private fun signUpUiStateLoading() = binding.run {
        signInProgress.showOrHide(true)
        signInButton.isEnabled = false
    }

    private fun signUpUiStateSuccess(userData: UserData) = binding.run {
        signInProgress.showOrHide(false)
        signInButton.isEnabled = true
        root.snackbar(getString(R.string.success_sign_in, userData.name, userData.email)).showSuccess()
    }

    private fun signUpUiStateError(error: Throwable) = binding.run {
        signInProgress.showOrHide(false)
        signInButton.isEnabled = true
        when (error) {
            is SignInException -> root.snackbar(R.string.error_sign_in).showError()
            is AuthUiException.Email -> emailInputLayout.error = getString(R.string.error_email_invalid)
            is AuthUiException.Password -> passwordInputLayout.error = getString(R.string.error_password_invalid)
            else -> root.snackbar(error.message).showError()
        }
    }
}