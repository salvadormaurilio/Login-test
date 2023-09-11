package mx.android.buabap.core.ui

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.cleanErrorWatcher() =
    editText?.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            error = String.empty()
            isErrorEnabled = false
        }
    })
