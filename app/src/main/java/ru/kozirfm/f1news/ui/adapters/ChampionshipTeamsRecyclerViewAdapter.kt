package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.databinding.ItemChampionshipTeamsTableBinding

class ChampionshipTeamsRecyclerViewAdapter :
    RecyclerView.Adapter<ChampionshipTeamsRecyclerViewAdapter.ChampionshipTeamsViewHolder>() {

    lateinit var itemChampionshipTeamsTableBinding: ItemChampionshipTeamsTableBinding

    var teamsTable: List<Team> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipTeamsViewHolder {
        itemChampionshipTeamsTableBinding = ItemChampionshipTeamsTableBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampionshipTeamsViewHolder(itemChampionshipTeamsTableBinding.root)
    }

    override fun onBindViewHolder(holder: ChampionshipTeamsViewHolder, position: Int) {
        holder.bind(teamsTable[position])
    }

    override fun getItemCount(): Int {
        return teamsTable.count()
    }

    inner class ChampionshipTeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) = with(itemView) {
            itemChampionshipTeamsTableBinding
                .positionChampionshipTeamTableTextView.text =
                String.format("%02d", absoluteAdapterPosition + 1)
            itemChampionshipTeamsTableBinding
                .fullNameChampionshipTeamTableTextView.text = team.name
            itemChampionshipTeamsTableBinding
                .pointsChampionshipTeamTableTextView.text = String.format("%d", team.points)
        }
    }

}