package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.databinding.ItemChampionshipDriversTableBinding

class ChampionshipDriversRecyclerViewAdapter :
    RecyclerView.Adapter<ChampionshipDriversRecyclerViewAdapter.ChampionshipViewHolder>() {

    lateinit var itemChampionshipDriversBinding: ItemChampionshipDriversTableBinding

    var driversTable: List<Driver> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionshipViewHolder {
        itemChampionshipDriversBinding = ItemChampionshipDriversTableBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampionshipViewHolder(itemChampionshipDriversBinding.root)
    }

    override fun onBindViewHolder(holder: ChampionshipViewHolder, position: Int) {
        holder.bind(driversTable[position])
    }

    override fun getItemCount(): Int {
        return driversTable.count()
    }

    inner class ChampionshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(driver: Driver) = with(itemView) {
            itemChampionshipDriversBinding
                .positionChampionshipDriverTableTextView.text =
                String.format("%02d", driver.position)
            itemChampionshipDriversBinding
                .fullNameChampionshipDriverTableTextView.text =
                String.format("%s %s", driver.name, driver.surname)
            itemChampionshipDriversBinding
                .teamChampionshipDriverTableTextView.text = driver.team
            itemChampionshipDriversBinding
                .pointsChampionshipDriverTableTextView.text = String.format("%d", driver.points)
        }
    }
}