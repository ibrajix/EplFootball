/*
 * Written and Developed by Inuwa Ibrahim - https://linktr.ee/Ibrajix
 * All rights reserved
 */

package com.ibrajix.eplfootball.data.repo

import com.ibrajix.eplfootball.data.response.teams.TeamResponse
import com.ibrajix.eplfootball.network.ApiDataSource
import com.ibrajix.eplfootball.network.BaseDataSource
import com.ibrajix.eplfootball.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PremierLeagueTeamRepo @Inject constructor(private val apiDataSource: ApiDataSource) : BaseDataSource() {

    //get premiership clubs
    suspend fun getPremierLeagueTeams() : Flow<Resource<TeamResponse>> {
        return flow {
            val result = safeApiCall { apiDataSource.getPremierLeagueTeams() }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}