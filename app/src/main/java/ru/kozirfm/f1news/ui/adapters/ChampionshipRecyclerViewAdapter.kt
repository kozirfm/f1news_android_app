package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_championship_table.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Driver

class ChampionshipRecyclerViewAdapter :
    RecyclerView.Adapter<ChampionshipRecyclerViewAdapter.ChampionshipViewHolder>() {

    var championshipTable: List<Driver> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipViewHolder {
        return ChampionshipViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_championship_table, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChampionshipViewHolder, position: Int) {
        holder.bind(championshipTable[position])
    }

    override fun getItemCount(): Int {
        return championshipTable.count()
    }

    inner class ChampionshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(driver: Driver) = with(itemView) {
            positionChampionshipTableTextView.text = String.format("%02d", driver.position)
            fullNameChampionshipTableTextView.text =
                String.format("%s %s", driver.name, driver.surname)
            teamChampionshipTableTextView.text = driver.team
            pointsChampionshipTableTextView.text = String.format("%d", driver.points)
        }
    }
}