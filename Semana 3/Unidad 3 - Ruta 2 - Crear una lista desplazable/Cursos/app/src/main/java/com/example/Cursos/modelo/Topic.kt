package com.example.Cursos.modelo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nombre: Int,
    val cupos : Int,
    @DrawableRes val imagen : Int
)