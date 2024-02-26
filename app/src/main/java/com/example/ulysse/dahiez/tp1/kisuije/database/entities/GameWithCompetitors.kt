package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
@Entity
data class GameWithCompetitors(
    @Embedded
    val competitor: Competitor,

    @Relation(
        parentColumn = "id_game",
        entityColumn = "id_competitor"

    )
    val competitors: List<Competitor>
)