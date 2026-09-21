package com.example.spainguide.data

import com.example.spainguide.R
import com.example.spainguide.model.Category
import com.example.spainguide.model.Place

object LocalData {
    val categories = listOf(
        Category(1, R.string.category_cities, R.drawable.ic_launcher_foreground),
        Category(2, R.string.category_beaches, R.drawable.ic_launcher_foreground),
        Category(3, R.string.category_food, R.drawable.ic_launcher_foreground)
    )

    val places = listOf(
        Place(1, 1, R.string.place_madrid, R.string.desc_madrid, R.drawable.ic_launcher_background),
        Place(2, 1, R.string.place_barcelona, R.string.desc_barcelona, R.drawable.ic_launcher_background),
        Place(3, 2, R.string.place_ibiza, R.string.desc_ibiza, R.drawable.ic_launcher_background),
        Place(4, 2, R.string.place_mallorca, R.string.desc_mallorca, R.drawable.ic_launcher_background),
        Place(5, 3, R.string.place_tapas, R.string.desc_tapas, R.drawable.ic_launcher_background),
        Place(6, 3, R.string.place_paella, R.string.desc_paella, R.drawable.ic_launcher_background),
        Place(7, 1, R.string.place_sevilla, R.string.desc_sevilla, R.drawable.ic_launcher_background),
        Place(8, 1, R.string.place_granada, R.string.desc_granada, R.drawable.ic_launcher_background),
        Place(9, 2, R.string.place_la_concha, R.string.desc_la_concha, R.drawable.ic_launcher_background),
        Place(10, 2, R.string.place_tenerife, R.string.desc_tenerife, R.drawable.ic_launcher_background),
        Place(11, 3, R.string.place_pintxos, R.string.desc_pintxos, R.drawable.ic_launcher_background),
        Place(12, 3, R.string.place_churros, R.string.desc_churros, R.drawable.ic_launcher_background)
    )
}