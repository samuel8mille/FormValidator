package com.github.samuel8mille.formvalidator.validators

import androidx.annotation.StringRes
import com.github.samuel8mille.formvalidator.FieldValidation
import java.util.regex.Pattern

class UrlValidator(@param:StringRes private val errorRes: Int) : FieldValidation {
    companion object {
        private val URL_REGEX_PATTERN = Pattern.compile(
            "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})([/\\w .-]*)*/?$"
        )
    }

    override fun isValid(value: String) = URL_REGEX_PATTERN.matcher(value.lowercase()).matches()
    override fun errorMessageRes() = errorRes
}