package com.org.watchmovie.data.network.models

import com.google.gson.Gson

data class RespProductionCountryDTO(
    val iso_3166_1: String,
    val name: String
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}