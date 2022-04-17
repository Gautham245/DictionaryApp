package com.example.dictionaryapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.dictionaryapp.data.local.Converters
import com.example.dictionaryapp.data.local.db.WordInfoDao
import com.example.dictionaryapp.data.local.db.WordInfoDatabase
import com.example.dictionaryapp.data.remote.DictionaryApi
import com.example.dictionaryapp.data.util.GsonParser
import com.example.dictionaryapp.domin.repository.WordInfoRepoImpl
import com.example.dictionaryapp.domin.repository.WordInfoRepository
import com.example.dictionaryapp.domin.usercase.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepoImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}