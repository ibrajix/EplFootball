/*
 * Written and Developed by Inuwa Ibrahim - https://linktr.ee/Ibrajix
 * All rights reserved
 */

package com.ibrajix.eplfootball.ui.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.data.response.players.Squad
import com.ibrajix.eplfootball.databinding.RcvLytPlayersBinding
import com.ibrajix.eplfootball.utils.Utility
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class PlayersAdapter(private val onClickListener: OnPlayersClickListener) : ListAdapter<Squad, PlayersAdapter.CompetitionsViewHolder>(
    PlayersDiffCallback()
) {

    class CompetitionsViewHolder private constructor(private val binding: RcvLytPlayersBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Squad?){

            binding.model = item
            binding.executePendingBindings()

            //get birth year
            val birthYear = item?.let { Utility.getBirthYearAfterDash(it.dateOfBirth) }

            // setup
            val birthDate = birthYear?.toInt()?.let { LocalDate.of(it, 1, 1) }
            val currentDate = LocalDate.now()

            //calculate birth year
            val age = Utility.calculateAge(birthDate, currentDate)

            binding.txtAge.text = itemView.context.getString(R.string.age_years, age.toString())
        }

        companion object{
            fun from(parent: ViewGroup) : CompetitionsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RcvLytPlayersBinding.inflate(layoutInflater, parent, false)
                return CompetitionsViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        return CompetitionsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
        holder.itemView.setOnClickListener {
            if (item != null) {
                onClickListener.onClickPlayers(item)
            }
        }
    }

    class OnPlayersClickListener(val clickListener: (squad: Squad) -> Unit){
        fun onClickPlayers(squad: Squad) = clickListener(squad)
    }


    class PlayersDiffCallback : DiffUtil.ItemCallback<Squad>() {

        override fun areItemsTheSame(oldItem: Squad, newItem: Squad): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Squad, newItem: Squad): Boolean {
            return oldItem == newItem
        }

    }

}