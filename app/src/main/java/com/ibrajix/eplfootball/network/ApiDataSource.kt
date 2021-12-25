/*
 * Written and Developed by Inuwa Ibrahim - https://linktr.ee/Ibrajix
 * All rights reserved
 */

package com.ibrajix.eplfootball.network

import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    //get premier league teams
    suspend fun getPremierLeagueTeams() = apiService.getPremierLeagueTeams()

    //get premier league team players
    suspend fun getPremierLeagueTeamPlayers(id: String) = apiService.getPremierLeagueTeamPlayers(id)

}