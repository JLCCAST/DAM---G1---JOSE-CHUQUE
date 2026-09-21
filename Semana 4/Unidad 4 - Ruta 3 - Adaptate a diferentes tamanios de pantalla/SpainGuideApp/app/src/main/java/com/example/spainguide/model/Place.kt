package com.example.spainguide.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Int,
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int
)

data class Place(
    val id: Int,
    val categoryId: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)