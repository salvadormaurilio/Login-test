package mx.android.buabap.ui.singin

import mx.android.buabap.core.TestDispatcherRule
import mx.android.buabap.data.AuthRepository
import mx.android.buabap.domain.SignInUseCase
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

class SingInViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val signUpUseCase = Mockito.mock<SignInUseCase>()

    private lateinit var singInViewModel: SingInViewModel

    @Before
    fun setup() {
        singInViewModel = SingInViewModel(signUpUseCase)
    }
}