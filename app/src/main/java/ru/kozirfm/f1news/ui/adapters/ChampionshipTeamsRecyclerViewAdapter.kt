package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_championship_teams_table.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Team

class ChampionshipTeamsRecyclerViewAdapter :
    RecyclerView.Adapter<ChampionshipTeamsRecyclerViewAdapter.ChampionshipTeamsViewHolder>() {

    var teamsTable: List<Team> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipTeamsViewHolder {
        return ChampionshipTeamsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_championship_teams_table, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChampionshipTeamsViewHolder, position: Int) {
        holder.bind(teamsTable[position])
    }

    override fun getItemCount(): Int {
        return teamsTable.count()
    }

    class ChampionshipTeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) = with(itemView) {
            positionChampionshipTeamTableTextView.text = String.format("%02d", absoluteAdapterPosition + 1)
            fullNameChampionshipTeamTableTextView.text = team.name
            pointsChampionshipTeamTableTextView.text = String.format("%d", team.points)
        }
    }

}