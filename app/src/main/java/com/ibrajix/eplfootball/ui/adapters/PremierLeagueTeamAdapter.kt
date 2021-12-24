package com.ibrajix.eplfootball.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibrajix.eplfootball.data.response.teams.Team
import com.ibrajix.eplfootball.databinding.RcvLytPremierLeagueTeamsBinding
import com.ibrajix.eplfootball.utils.loadSvg


class PremierLeagueTeamAdapter (private val onClickListener: OnTeamItemClickListener) : ListAdapter<Team, PremierLeagueTeamAdapter.PremierLeagueViewHolder>(
    PremierLeagueDiffCallback()
) {


    class PremierLeagueViewHolder private constructor(private val binding: RcvLytPremierLeagueTeamsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Team?){
            if (item != null) {
                binding.imgClub.loadSvg(item.crestUrl)
            }
        }
        companion object{
            fun from(parent: ViewGroup) : PremierLeagueViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RcvLytPremierLeagueTeamsBinding.inflate(layoutInflater, parent, false)
                return PremierLeagueViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremierLeagueViewHolder {
        return PremierLeagueViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PremierLeagueViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
        holder.itemView.setOnClickListener {
            if (item != null) {
                onClickListener.onClickTeam(item)
            }
        }
    }

    class OnTeamItemClickListener(val clickListener: (team: Team) -> Unit){
        fun onClickTeam(team: Team) = clickListener(team)
    }


    class  PremierLeagueDiffCallback : DiffUtil.ItemCallback<Team>() {

        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }

    }


}