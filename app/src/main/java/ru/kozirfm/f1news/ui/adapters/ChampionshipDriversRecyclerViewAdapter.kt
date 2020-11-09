package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_championship_drivers_table.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Driver

class ChampionshipDriversRecyclerViewAdapter :
    RecyclerView.Adapter<ChampionshipDriversRecyclerViewAdapter.ChampionshipViewHolder>() {

    var driversTable: List<Driver> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipViewHolder {
        return ChampionshipViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_championship_drivers_table, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChampionshipViewHolder, position: Int) {
        holder.bind(driversTable[position])
    }

    override fun getItemCount(): Int {
        return driversTable.count()
    }

    class ChampionshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(driver: Driver) = with(itemView) {
            positionChampionshipDriverTableTextView.text = String.format("%02d", driver.position)
            fullNameChampionshipDriverTableTextView.text =
                String.format("%s %s", driver.name, driver.surname)
            teamChampionshipDriverTableTextView.text = driver.team
            pointsChampionshipDriverTableTextView.text = String.format("%d", driver.points)
        }
    }
}