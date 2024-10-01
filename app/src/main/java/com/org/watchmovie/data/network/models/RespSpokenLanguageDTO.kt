package com.org.watchmovie.data.network.models

import com.google.gson.Gson

data class RespSpokenLanguageDTO(
    val english_name: String,
    val iso_639_1: String,
    val name: String
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}