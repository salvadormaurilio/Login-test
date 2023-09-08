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
    fun `return NoValidationNeededException exception when areInvalidSingUpCredentials is called and userCredentialsUi are valid`() {
        val userCredentialsUi = givenUserCredentialsUi()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.second, AuthUiException.NoValidationNeededException)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return NameException when areInvalidSingUpCredentials is called and userCredentialsUi has name invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidName()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.NameException)
    }

    @Test
    fun `return EmailException when areInvalidSingUpCredentials is called and userCredentialsUi has email invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidEmail()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.EmailException)
    }

    @Test
    fun `return PasswordException when areInvalidSingUpCredentials is called and userCredentialsUi has password invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidPassword()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.PasswordException)
    }

    @Test
    fun `return ConfirmPasswordException when areInvalidSingUpCredentials is called and userCredentialsUi has confirm password invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidConfirmPassword()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.ConfirmPasswordException)
    }

    @Test
    fun `return DifferentPasswordException when areInvalidSingUpCredentials is called and userCredentialsUi has different passwords invalid`() {
        val userCredentialsUi = givenUserCredentialsUiWithDifferentPasswords()

        val result = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)

        assertThatEquals(result.first, true)
        assertThatEquals(result.second, AuthUiException.DifferentPasswordException)
    }

    @Test
    fun `return NoValidationNeededException when areInvalidSingInCredentials is called and email and password are valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.NoValidationNeededException)
        assertThatEquals(result.first, false)
    }

    @Test
    fun `return EmailException when areInvalidSingInCredentials is called and email is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(result.second, AuthUiException.EmailException)
        assertThatEquals(result.first, true)
    }

    @Test
    fun `return PasswordException when areInvalidSingInCredentials is called and password is valid`() {
        val result = authExceptionHandler.areInvalidSingInCredentials(ANY_USER_EMAIL, ANY_INVALID_PASSWORD)

        assertThatEquals(result.second, AuthUiException.PasswordException)
        assertThatEquals(result.first, true)
    }
}