package com.brunadev.devheroapp.login.data.model

import android.graphics.Color

enum class Priority {

    FINISHED,
    IN_PROGRESS,
    STARTED,
    OTHER;


    fun getColor() = when(this){
        FINISHED -> Color.GRAY
        IN_PROGRESS -> Color.BLUE
        STARTED -> Color.GREEN
        else -> Color.TRANSPARENT
    }
}