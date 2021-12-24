package com.ibrajix.eplfootball.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.eplfootball.data.repo.PremierLeagueTeamRepo
import com.ibrajix.eplfootball.data.response.teams.Team
import com.ibrajix.eplfootball.data.response.teams.TeamResponse
import com.ibrajix.eplfootball.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PremierLeagueTeamViewModel @Inject constructor(private val premierLeagueTeamRepo: PremierLeagueTeamRepo) : ViewModel() {

    //get premier league teams using shared flow
    private val _premierLeagueTeams = MutableSharedFlow<Resource<TeamResponse>>()
    val premierLeagueTeams: SharedFlow<Resource<TeamResponse>> = _premierLeagueTeams

    fun doGetPremierLeagueTeams(){
        viewModelScope.launch {
            premierLeagueTeamRepo.getPremierLeagueTeams()
                .catch { e->
                    _premierLeagueTeams.emit(Resource.error(e.toString()))
                }
                .collect {
                    _premierLeagueTeams.emit(it)
                }
        }
    }

}