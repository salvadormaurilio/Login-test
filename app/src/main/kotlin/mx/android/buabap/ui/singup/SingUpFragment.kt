package mx.android.buabap.ui.singup

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
import mx.android.buabap.databinding.ActivitySignUpBinding
import mx.android.buabap.ui.exception.AuthUiException
import mx.android.buabap.ui.singin.UserCredentialsUi

@AndroidEntryPoint
class SingUpFragment : Fragment() {

    private var binding: ActivitySignUpBinding? = null

    private val singUpViewModel by viewModels<SingUpViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        lifecycleScope.launch { collectSignUpUiState() }
    }

    private fun initUi() = binding?.run {
        confirmSignUpButton.setOnClickListener {
            CountingIdlingResourceSingleton.increment()
            singUpViewModel.singUp(buildUserCredentialsUi())
        }
    }

    private fun buildUserCredentialsUi() = UserCredentialsUi(
        name = binding?.nameEditText?.getString().orEmpty(),
        email = binding?.emailEditText?.getString().orEmpty(),
        password = binding?.passwordEditText?.getString().orEmpty(),
        confirmPassword = binding?.confirmPasswordEditText?.getString().orEmpty()
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

    private fun signUpUiStateLoading() = binding?.run {
        signUpProgress.showOrHide(true)
        confirmSignUpButton.isEnabled = false
    }

    private fun signUpUiStateSuccess() = binding?.run {
        signUpProgress.showOrHide(false)
        confirmSignUpButton.isEnabled = true
        requireContext().showAlertDialog(getString(R.string.success_sign_up)) { findNavController().popBackStack() }
        CountingIdlingResourceSingleton.decrement()
    }

    private fun signUpUiStateError(error: Throwable) = binding?.run {
        signUpProgress.showOrHide(false)
        confirmSignUpButton.isEnabled = true
        when (error) {
            is AuthException.UserAlreadyExistException -> requireContext().showAlertDialog(getString(R.string.error_user_already_exit))
            is AuthException.SignUpException -> requireContext().showAlertDialog(getString(R.string.error_sign_up))
            is AuthUiException.NameException -> nameInputLayout.error = getString(R.string.error_name_invalid)
            is AuthUiException.EmailException -> emailInputLayout.error = getString(R.string.error_email_invalid)
            is AuthUiException.PasswordException -> passwordInputLayout.error = getString(R.string.error_password_invalid)
            is AuthUiException.ConfirmPasswordException -> confirmPasswordInputLayout.error = getString(R.string.error_password_invalid)
            is AuthUiException.DifferentPasswordException -> confirmPasswordInputLayout.error = getString(R.string.error_password_different)
            else -> root.snackbar(error.message).showError()
        }
        CountingIdlingResourceSingleton.decrement()
    }
}
