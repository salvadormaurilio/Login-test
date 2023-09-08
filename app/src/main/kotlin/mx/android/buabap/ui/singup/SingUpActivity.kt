package mx.android.buabap.ui.singup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mx.android.buabap.core.ui.getString
import mx.android.buabap.databinding.ActivitySignUpBinding
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
}
