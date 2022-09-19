package model

import java.util.*

data class CatResponse(
    val id: UUID,
    val name: String,
    val breed: String,
)