package com.turingalan.examen.data.remote.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("docs") val docs: List<bookDto>
)

data class bookDto(
    @SerializedName("key") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("author_name") val authors: List<String> = listOf(),
    @SerializedName("first_publish_year") val publicationYear: Int,
)