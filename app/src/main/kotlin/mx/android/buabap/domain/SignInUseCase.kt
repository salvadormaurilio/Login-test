package mx.android.buabap.domain

import mx.android.buabap.data.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun signIn(email: String, password: String) = authRepository.signIn(email, password)
}