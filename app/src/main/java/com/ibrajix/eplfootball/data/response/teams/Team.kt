package com.ibrajix.eplfootball.data.response.teams

import java.io.Serializable

data class Team(
    val address: String,
    val clubColors: String,
    val crestUrl: String,
    val email: String,
    val founded: Int,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val phone: String,
    val shortName: String,
    val tla: String,
    val venue: String,
    val website: String
) : Serializable