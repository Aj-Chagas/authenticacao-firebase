package br.com.anderson.chagas.autheticationdesire.helpers

import java.util.regex.Pattern

const val EMAIL_REGEX = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
const val MIN_CREDENTIAL_LENGTH = 6
const val EXPECTED_STANDARD = "($1) $2-$3"
const val PHONE_REGEX = "([0-9]{2})([0-9]{4,5})([0-9]{4})"
const val CPF_LENGTH = 11

fun isEmailValid(email: String) = Pattern.matches(EMAIL_REGEX, email)

fun isPasswordValid(password: String) = password.length >= MIN_CREDENTIAL_LENGTH

fun isMinCharacter(text: String, min: Int) = text.length >= min

fun isMinCharacterToPhone(text: String) = text.length >= 11

fun formatNumberPhone(text: String) = text.replace(PHONE_REGEX.toRegex(), EXPECTED_STANDARD)

fun isUsernameValid(username: String) = username.length >= MIN_CREDENTIAL_LENGTH

fun isCpfValid(cpf: String): Boolean = cpf.length == CPF_LENGTH

fun arePasswordsSame(password: String, repeatPassword: String) = isPasswordValid(password) && isPasswordValid(repeatPassword) && password == repeatPassword


