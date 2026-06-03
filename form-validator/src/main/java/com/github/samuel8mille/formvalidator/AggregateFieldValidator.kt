package com.github.samuel8mille.formvalidator

interface AggregateFieldValidator {
    val validations: List<FieldValidation>

    fun validate(value: String): Int? =
        validations.firstOrNull { it.isNotValid(value) }?.errorMessageRes()

    fun validate(value: String, extra: List<FieldValidation>): Int? =
        (validations + extra).firstOrNull { it.isNotValid(value) }?.errorMessageRes()
}