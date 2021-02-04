package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_grand_prix.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.GrandPrix

class ChampCalendarRecyclerViewAdapter :
    RecyclerView.Adapter<ChampCalendarRecyclerViewAdapter.ChampCalendarViewHolder>() {

    var calendar: List<GrandPrix> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampCalendarViewHolder {
        return ChampCalendarViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grand_prix, parent, false))
    }

    override fun onBindViewHolder(holder: ChampCalendarViewHolder, position: Int) {
        holder.bind(calendar[position])
    }

    override fun getItemCount(): Int {
        return calendar.count()
    }

    inner class ChampCalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(grandPrix: GrandPrix) = with(itemView) {
            grandPrixDateTextView.text = grandPrix.date
            grandPrixFlagImageView.load(grandPrix.flag)
            grandPrixNameTextView.text = grandPrix.grandPrix
            grandPrixTrackTextView.text = grandPrix.track
            grandPrixLengthTextView.text = grandPrix.length
            grandPrixLapsTextView.text = grandPrix.laps
            grandPrixDistanceTextView.text = grandPrix.distance
            grandPrixDriverTextView.text = grandPrix.driver
            grandPrixTeamTextView.text = grandPrix.team
        }
    }

}
