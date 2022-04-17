package com.example.dictionaryapp.domin.model

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: String?,
    val phonetic: String?,
    val word: String
)
