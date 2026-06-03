# FormValidator

Android library for composable field validation.

## Contents

| Class | Description |
|---|---|
| `FieldValidation` | Contract for a single validation rule |
| `AggregateFieldValidator` | Runs a list of `FieldValidation` rules and returns the first failure |
| `EmptyTextValidator` | Fails if the value is an empty string |
| `UrlValidator` | Fails if the value is not a well-formed URL |

## Setup

Add JitPack to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        maven("https://jitpack.io")
    }
}
```

Add the dependency:

```kotlin
implementation("com.github.samuel8mille:form-validator:1.0.0")
```

## Usage

### Built-in validators

`EmptyTextValidator` and `UrlValidator` take a `@StringRes` error resource as a constructor parameter:

```kotlin
val emptyValidator = EmptyTextValidator(R.string.error_empty)
val urlValidator   = UrlValidator(R.string.error_invalid_url)
```

### Custom validator

Implement `FieldValidation` to define your own rule:

```kotlin
class MaxLengthValidator(
    private val max: Int,
    @param:StringRes private val errorRes: Int,
) : FieldValidation {
    override fun isValid(value: String) = value.length <= max
    override fun errorMessageRes() = errorRes
}
```

### Aggregate validator

Implement `AggregateFieldValidator` to run multiple rules in order. `validate()` returns the `@StringRes` of the first failing rule, or `null` if all pass:

```kotlin
class UrlInputValidator(
    validations: List<FieldValidation>
) : AggregateFieldValidator {
    override val validations = validations
}

val validator = UrlInputValidator(listOf(
    EmptyTextValidator(R.string.error_empty),
    UrlValidator(R.string.error_invalid_url),
))

val errorRes: Int? = validator.validate(userInput)
```

Pass extra rules at call time without changing the base set — useful for stateful checks like duplicate detection:

```kotlin
val duplicateRule = object : FieldValidation {
    override fun isValid(value: String) = existingUrls.none { it == value }
    override fun errorMessageRes() = R.string.error_duplicate
}

val errorRes = validator.validate(userInput, extra = listOf(duplicateRule))
```