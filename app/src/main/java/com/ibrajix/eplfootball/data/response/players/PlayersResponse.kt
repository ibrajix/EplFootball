package com.ibrajix.eplfootball.data.response.players

data class PlayersResponse(
    val address: String,
    val area: AreaX,
    val clubColors: String,
    val crestUrl: String,
    val email: Any,
    val founded: Int,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val phone: String,
    val shortName: String,
    val squad: List<Squad>,
    val tla: String,
    val venue: String,
    val website: String
)