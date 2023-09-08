package mx.android.buabap.ui.singin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mx.android.buabap.databinding.ActivitySignInBinding

@AndroidEntryPoint
class SingInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val singInViewModel by viewModels<SingInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() = binding.run {
        signInButton.setOnClickListener {
            //   authViewModel.signIn(email = emailEditText.getString(), password = passwordEditText.getString())
        }
    }
}