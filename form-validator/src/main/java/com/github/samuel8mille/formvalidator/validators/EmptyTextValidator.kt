package com.github.samuel8mille.formvalidator.validators

import androidx.annotation.StringRes
import com.github.samuel8mille.formvalidator.FieldValidation

class EmptyTextValidator(@param:StringRes private val errorRes: Int) : FieldValidation {
    override fun isValid(value: String) = value.isNotEmpty()
    override fun errorMessageRes() = errorRes
}