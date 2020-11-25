package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_article_image.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.News
import ru.kozirfm.f1news.data.entites.News.NewsSimple
import ru.kozirfm.f1news.data.entites.News.NewsWithImage

class NewsRecyclerViewAdapter(val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var news: List<News> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> NewsViewHolder(inflater.inflate(R.layout.item_article, parent, false))
            else -> NewsWithImageViewHolder(
                inflater.inflate(
                    R.layout.item_article_image,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> holder.bind(news[position] as NewsSimple)
            is NewsWithImageViewHolder -> holder.bind(news[position] as NewsWithImage)
        }
    }

    override fun getItemCount(): Int {
        return news.count()
    }

    override fun getItemViewType(position: Int): Int {
        return when (news[position]) {
            is NewsSimple -> 0
            is NewsWithImage -> 1
        }
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: NewsSimple) = with(itemView) {
            articleTitleTextView.text = news.title
            articleDateTextView.text = news.date

            itemView.setOnClickListener {
                itemClick.invoke(news.text)
            }

        }

    }

    inner class NewsWithImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: NewsWithImage) = with(itemView) {
            articleTitleTextView.text = news.title
            articleDateTextView.text = news.date
            articleImageView.load(news.images[0]) {
                transformations(CircleCropTransformation())
            }

            itemView.setOnClickListener {
                itemClick.invoke(news.text)
            }

        }

    }
}
