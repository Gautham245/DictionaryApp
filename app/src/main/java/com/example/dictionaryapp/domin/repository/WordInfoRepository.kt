package com.example.dictionaryapp.domin.repository

import com.example.dictionaryapp.common.util.Resource
import com.example.dictionaryapp.domin.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}