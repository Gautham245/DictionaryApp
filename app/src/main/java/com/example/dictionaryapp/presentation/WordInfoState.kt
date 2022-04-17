package com.example.dictionaryapp.presentation

import com.example.dictionaryapp.domin.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)