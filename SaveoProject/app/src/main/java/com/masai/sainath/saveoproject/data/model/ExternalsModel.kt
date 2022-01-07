package com.masai.sainath.saveoproject.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExternalsModel(

    @field:SerializedName("thetvdb")
    val thetvdb: Int? = null,

    @field:SerializedName("imdb")
    val imdb: String? = null,

    @field:SerializedName("tvrage")
    val tvrage: Int? = null
) : Serializable