package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_article_image.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.News
import ru.kozirfm.f1news.data.entites.NewsSimple
import ru.kozirfm.f1news.data.entites.NewsWithImage

class NewsAdapter(private val downloaded: () -> Unit, val itemClick: (News) -> Unit) :
    PagingDataAdapter<News, RecyclerView.ViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> NewsPagerViewHolder(inflater.inflate(R.layout.item_article, parent, false))
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
        downloaded.invoke()
        val currentItem = getItem(position)
        if (currentItem != null) {
            when (holder) {
                is NewsPagerViewHolder -> holder.bind(currentItem as NewsSimple)
                is NewsWithImageViewHolder -> holder.bind(currentItem as NewsWithImage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NewsSimple -> 0
            else -> 1
        }
    }

    inner class NewsPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: NewsSimple) = with(itemView) {
            articleTitleTextView.text = news.title
            articleDateTextView.text = news.date

            itemView.setOnClickListener {
                itemClick.invoke(news)
            }
        }
    }

    inner class NewsWithImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(news: NewsWithImage) = with(itemView) {
            articleImageTitleTextView.text = news.title
            articleImageDateTextView.text = news.date
            articleImageView.load(news.images[0])

            itemView.setOnClickListener {
                itemClick.invoke(news)
            }
        }
    }

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.getItemId() == newItem.getItemId()
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }

        }
    }

}