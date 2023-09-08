package mx.android.buabap.ui.exception

import mx.android.buabap.ANY_INVALID_PASSWORD
import mx.android.buabap.ANY_INVALID_USER_EMAIL
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.givenUserCredentialsUi
import mx.android.buabap.givenUserCredentialsUiWithDifferentPasswords
import mx.android.buabap.givenUserCredentialsUiWithInvalidConfirmPassword
import mx.android.buabap.givenUserCredentialsUiWithInvalidEmail
import mx.android.buabap.givenUserCredentialsUiWithInvalidName
import mx.android.buabap.givenUserCredentialsUiWithInvalidPassword
import org.junit.Before
import org.junit.Test

class AuthExceptionHandlerShould {

    private lateinit var authExceptionHandler: AuthExceptionHandler

    @Before
    fun setup() {
        authExceptionHandler = AuthExceptionHandler()
    }

    @Test
    fun `return NoValidationNeeded exception when areInvalidSingUpCredentials is called and userCredentialsUi are valid`() {
        val userCredentialsUi = givenUserCredentialsUi()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.second, AuthUiException.NoValidationNeeded)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return Name exception when areInvalidSingUpCredentials is called and userCredentialsUi has name invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidName()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.Name)
    }

    @Test
    fun `return Email exception when areInvalidSingUpCredentials is called and userCredentialsUi has email invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidEmail()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.Email)
    }

    @Test
    fun `return Password exception when areInvalidSingUpCredentials is called and userCredentialsUi has password invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidPassword()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.Password)
    }

    @Test
    fun `return ConfirmPassword exception when areInvalidSingUpCredentials is called and userCredentialsUi has confirm password invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidConfirmPassword()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.ConfirmPassword)
    }

    @Test
    fun `return DifferentPassword exception when areInvalidSingUpCredentials is called and userCredentialsUi has different passwords invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithDifferentPasswords()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.DifferentPassword)
    }

    @Test
    fun `return NoValidationNeeded exception when areInvalidSingInCredentials is called and email and password are valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.NoValidationNeeded)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return Email exception when areInvalidSingInCredentials is called and email is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.Email)
        assertThatEquals(result.first, true)
    }

    @Test
    fun `return Password exception when areInvalidSingInCredentials is called and password is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_INVALID_PASSWORD)

        assertThatEquals(result.second, AuthUiException.Password)
        assertThatEquals(result.first, true)
    }
}