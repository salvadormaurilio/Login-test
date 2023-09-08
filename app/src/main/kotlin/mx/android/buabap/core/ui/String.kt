package mx.android.buabap.core.ui

import java.util.regex.Pattern


fun String.isValidEmail() = Pattern.compile(EMAIL_PATTERN).matcher(this).matches()

private const val EMAIL_PATTERN =
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
            ")+"