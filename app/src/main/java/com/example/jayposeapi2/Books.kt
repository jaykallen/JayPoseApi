package com.example.jayposeapi2

typealias Books = List<Book>;

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val status: String,
    val fee: Double,
    val lastEdited: String,
)
