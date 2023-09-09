package mx.android.buabap.ui.singup

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
import mx.android.buabap.core.ui.showAlertDialog
import mx.android.buabap.core.ui.showError
import mx.android.buabap.core.ui.showOrHide
import mx.android.buabap.core.ui.snackbar
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.databinding.ActivitySignUpBinding
import mx.android.buabap.ui.exception.AuthUiException
import mx.android.buabap.ui.singin.UserCredentialsUi

@AndroidEntryPoint
class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private val singUpViewModel by viewModels<SingUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        lifecycleScope.launch { collectSignUpUiState() }
    }

    private fun initUi() = binding.run {
        signUpButton.setOnClickListener {
            singUpViewModel.singUp(buildUserCredentialsUi())
        }
    }

    private fun buildUserCredentialsUi() = UserCredentialsUi(
        name = binding.nameEditText.getString(),
        email = binding.emailEditText.getString(),
        password = binding.passwordEditText.getString(),
        confirmPassword = binding.confirmPasswordEditText.getString()
    )

    private suspend fun collectSignUpUiState() {
        singUpViewModel.signUpUiState
            .flowWithLifecycle(lifecycle, CREATED)
            .collect { signUpUiState -> handleSignUpUiState(signUpUiState) }
    }

    private fun handleSignUpUiState(signUpUiState: SignUpUiState?) = signUpUiState?.run {
        when (this) {
            is SignUpUiState.Loading -> signUpUiStateLoading()
            is SignUpUiState.Success -> signUpUiStateSuccess()
            is SignUpUiState.Error -> signUpUiStateError(error)
        }
    }

    private fun signUpUiStateLoading() = binding.run {
        signUpProgress.showOrHide(true)
        signUpButton.isEnabled = false
    }

    private fun signUpUiStateSuccess() = binding.run {
        signUpProgress.showOrHide(false)
        signUpButton.isEnabled = true
        showAlertDialog(getString(R.string.success_sign_up)) { finish() }
    }

    private fun signUpUiStateError(error: Throwable) = binding.run {
        signUpProgress.showOrHide(false)
        signUpButton.isEnabled = true
        when (error) {
            is AuthException.UserAlreadyExistException -> showAlertDialog(getString(R.string.error_user_already_exit))
            is AuthException.SignUpException -> showAlertDialog(getString(R.string.error_sign_up))
            is AuthUiException.NameException -> nameInputLayout.error = getString(R.string.error_name_invalid)
            is AuthUiException.EmailException -> emailInputLayout.error = getString(R.string.error_email_invalid)
            is AuthUiException.PasswordException -> passwordInputLayout.error = getString(R.string.error_password_invalid)
            is AuthUiException.ConfirmPasswordException -> confirmPasswordInputLayout.error = getString(R.string.error_password_invalid)
            is AuthUiException.DifferentPasswordException -> confirmPasswordInputLayout.error = getString(R.string.error_password_different)
            else -> root.snackbar(error.message).showError()
        }
    }
}
