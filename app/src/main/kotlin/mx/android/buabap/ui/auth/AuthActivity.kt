package mx.android.buabap.ui.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.android.buabap.core.ui.intentTo
import mx.android.buabap.databinding.ActivityAuthBinding
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp
import mx.android.buabap.ui.singin.SingInActivity
import mx.android.buabap.ui.singup.SingUpActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            is SignUp -> openSingUpActivity()
            is SignIn -> openSingInActivity()
        }
    }

    private fun openSingUpActivity() = intentTo<SingUpActivity>().run { startActivity(this) }

    private fun openSingInActivity() = intentTo<SingInActivity>().run { startActivity(this) }
}