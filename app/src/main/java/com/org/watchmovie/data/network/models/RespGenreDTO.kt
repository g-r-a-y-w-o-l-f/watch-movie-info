package com.org.watchmovie.data.network.models

import com.google.gson.Gson

data class RespGenreDTO(
    val id: Int,
    val name: String
) {
    fun toJson(): String {
        return Gson().toJson(this).toString()
    }
}