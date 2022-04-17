package com.example.dictionaryapp.domin.usercase

import com.example.dictionaryapp.common.util.Resource
import com.example.dictionaryapp.domin.model.WordInfo
import com.example.dictionaryapp.domin.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(private val repository: WordInfoRepository) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {

        if (word.isBlank()) {
            return flow {
                emit(Resource.Failure(message = "Please Enter Data", data =  emptyList()))
            }
        }
        return repository.getWordInfo(word = word)
    }
}