package mx.android.buabap.ui.singup

import mx.android.buabap.core.TestDispatcherRule
import mx.android.buabap.domain.SignInUseCase
import mx.android.buabap.domain.SignUpUseCase
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

class SingUpViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val signUpUseCase = Mockito.mock<SignUpUseCase>()

    private lateinit var singUpViewModel: SingUpViewModel

    @Before
    fun setup() {
        singUpViewModel = SingUpViewModel(signUpUseCase)
    }
}