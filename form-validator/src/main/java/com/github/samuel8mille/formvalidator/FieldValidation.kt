package com.github.samuel8mille.formvalidator

interface FieldValidation {
    fun isValid(value: String): Boolean
    fun isNotValid(value: String): Boolean = isValid(value).not()
    fun errorMessageRes(): Int
}