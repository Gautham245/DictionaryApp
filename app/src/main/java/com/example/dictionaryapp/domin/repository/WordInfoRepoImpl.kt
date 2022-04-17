package com.example.dictionaryapp.domin.repository

import com.example.dictionaryapp.common.util.Resource
import com.example.dictionaryapp.data.local.db.WordInfoDao
import com.example.dictionaryapp.data.remote.DictionaryApi
import com.example.dictionaryapp.domin.model.WordInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepoImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfo = api.getMeaning(word)
            dao.deleteWordInfos(remoteWordInfo.map { it.word })
            dao.intsertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })

        } catch (e: HttpException) {
            emit(
                Resource.Failure(
                    message = "Oops Some thing went Wrong",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Failure(
                    message = "Couldn't Reach server, Check Internet Connection",
                    data = wordInfos
                )
            )
        }

        val newWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}