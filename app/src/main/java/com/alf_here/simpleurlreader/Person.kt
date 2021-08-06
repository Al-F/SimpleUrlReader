package com.alf_here.simpleurlreader

data class Person(
    val firstName: String,
    val lastName: String,
    val gender: String,
    val age: Int,
    val address: PersonAddress,
    val phoneNumbers: List<PersonPhone>
) {
}

data class PersonPhone(val type: String, val number: String)

data class PersonAddress(
    val streetAddress: String,
    val city: String,
    val state: String,
    val postalCode: String
)
