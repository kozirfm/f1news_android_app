package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kozirfm.f1news.data.entites.GrandPrix
import ru.kozirfm.f1news.databinding.ItemGrandPrixBinding

class ChampCalendarRecyclerViewAdapter :
    RecyclerView.Adapter<ChampCalendarRecyclerViewAdapter.ChampCalendarViewHolder>() {

    lateinit var itemGrandPrix: ItemGrandPrixBinding

    var calendar: List<GrandPrix> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampCalendarViewHolder {
        itemGrandPrix = ItemGrandPrixBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ChampCalendarViewHolder(itemGrandPrix.root)
    }

    override fun onBindViewHolder(holder: ChampCalendarViewHolder, position: Int) {
        holder.bind(calendar[position])
    }

    override fun getItemCount(): Int {
        return calendar.count()
    }

    inner class ChampCalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(grandPrix: GrandPrix) = with(itemView) {
            itemGrandPrix.grandPrixDateTextView.text = grandPrix.date
            itemGrandPrix.grandPrixFlagImageView.load(grandPrix.flag)
            itemGrandPrix.grandPrixNameTextView.text = grandPrix.grandPrix
            itemGrandPrix.grandPrixTrackTextView.text = grandPrix.track
            itemGrandPrix.grandPrixLengthTextView.text = grandPrix.length
            itemGrandPrix.grandPrixLapsTextView.text = grandPrix.laps
            itemGrandPrix.grandPrixDistanceTextView.text = grandPrix.distance
            itemGrandPrix.grandPrixDriverTextView.text = grandPrix.driver
            itemGrandPrix.grandPrixTeamTextView.text = grandPrix.team
        }
    }

}
