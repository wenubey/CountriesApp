package com.wenubey.countriesapp.core

fun Map<String,String>.getKeyByValue(value: String): String? {
    return this.entries.firstOrNull {
        it.value.contains(value, ignoreCase = true
        ) }?.key
}

fun String?.nullCheck(): String =  this ?: "No info found"
