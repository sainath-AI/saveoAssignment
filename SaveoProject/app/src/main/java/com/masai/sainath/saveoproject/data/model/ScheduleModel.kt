package com.masai.sainath.saveoproject.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScheduleModel(

    @field:SerializedName("days")
    val days: List<String?>? = null,

    @field:SerializedName("time")
    val time: String? = null
) : Serializable