package com.masai.sainath.saveoproject.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageModel(

    @field:SerializedName("original")
    val original: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
) : Serializable