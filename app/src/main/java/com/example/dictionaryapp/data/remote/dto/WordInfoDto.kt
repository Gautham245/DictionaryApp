package com.example.dictionaryapp.data.remote.dto

import com.example.dictionaryapp.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val word: String
){
    fun toWordInfoEntity() : WordInfoEntity{
        return WordInfoEntity(
            word = word,
            origin = origin,
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic
        )
    }
}