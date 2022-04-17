package com.example.dictionaryapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionaryapp.domin.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: com.example.dictionaryapp.data.util.JsonParser) {

    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }
}