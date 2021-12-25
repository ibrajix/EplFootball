/*
 * Written and Developed by Inuwa Ibrahim - https://linktr.ee/Ibrajix
 * All rights reserved
 */

package com.ibrajix.eplfootball.data.repo

import com.ibrajix.eplfootball.data.response.players.PlayersResponse
import com.ibrajix.eplfootball.network.ApiDataSource
import com.ibrajix.eplfootball.network.BaseDataSource
import com.ibrajix.eplfootball.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlayersRepo @Inject constructor(private val apiDataSource: ApiDataSource) : BaseDataSource() {

    //get team players
    suspend fun getPremierLeagueTeamPlayers(id: String) : Flow<Resource<PlayersResponse>> {
        return flow {
            val result = safeApiCall { apiDataSource.getPremierLeagueTeamPlayers(id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}