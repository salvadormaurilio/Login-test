package mx.android.buabap.ui.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import mx.android.buabap.databinding.ActivityAuthBinding
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp

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
            .flowWithLifecycle(lifecycle, STARTED)
            .collect { authAction -> openAuthAction(authAction) }
    }

    private fun openAuthAction(authAction: AuthAction?) = authAction?.run {
        when (this) {
            is SignUp -> Snackbar.make(binding.root, "Sing Up", LENGTH_LONG).show()
            is SignIn -> Snackbar.make(binding.root, "Sing in", LENGTH_LONG).show()
        }
    }
}