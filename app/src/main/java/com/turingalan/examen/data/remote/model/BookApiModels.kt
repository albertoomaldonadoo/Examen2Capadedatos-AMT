package com.turingalan.examen.data.remote.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("results") val results: List<bookDto>
)

data class bookDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String> = listOf(),
    @SerializedName("publicationYear") val publicationYear: Int,
)