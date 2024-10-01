package com.org.watchmovie.data.network.models

import com.google.gson.Gson

data class RespProductionCompanyDTO(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
}