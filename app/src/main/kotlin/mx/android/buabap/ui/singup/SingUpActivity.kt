package mx.android.buabap.ui.singup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.android.buabap.databinding.ActivitySignUpBinding

class SingUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() = binding.run {
        signUpButton.setOnClickListener {

        }
    }
}