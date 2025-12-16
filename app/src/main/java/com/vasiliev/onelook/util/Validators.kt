package com.vasiliev.onelook.util

private val nameRegex = Regex("^[\\p{L} ]+$") // літери будь-якою мовою + пробіл
private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

fun isValidFullName(value: String): Boolean =
    value.isNotBlank() && nameRegex.matches(value.trim())

fun isValidEmail(value: String): Boolean =
    value.isNotBlank() && emailRegex.matches(value.trim())

fun isValidPassword(value: String): Boolean =
    value.length >= 8
