package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.databinding.ItemArticleBinding
import ru.kozirfm.f1news.databinding.ItemArticleImageBinding

class NewsAdapter(val itemClick: (Article) -> Unit) :
    PagingDataAdapter<Article, RecyclerView.ViewHolder>(DIFF_UTIL_CALLBACK) {

    lateinit var itemArticleBinding: ItemArticleBinding
    lateinit var itemArticleImageBinding: ItemArticleImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        itemArticleBinding = ItemArticleBinding.inflate(inflater, parent, false)
        itemArticleImageBinding = ItemArticleImageBinding.inflate(inflater, parent, false)
        return when (viewType) {
            0 -> NewsPagerViewHolder(itemArticleBinding.root)
            else -> NewsWithImageViewHolder(itemArticleImageBinding.root)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsPagerViewHolder -> getItem(position)?.let { holder.bind(it) }
            is NewsWithImageViewHolder -> getItem(position)?.let { holder.bind(it) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)?.images) {
            null -> 0
            else -> 1
        }
    }

    inner class NewsPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            itemArticleBinding.articleTitleTextView.text = article.title
            itemArticleBinding.articleDateTextView.text = article.date

            itemView.setOnClickListener {
                itemClick.invoke(article)
            }
        }
    }

    inner class NewsWithImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) = with(itemView) {
            itemArticleImageBinding.articleImageTitleTextView.text = article.title
            itemArticleImageBinding.articleImageDateTextView.text = article.date
            itemArticleImageBinding.articleImageView.load(article.images?.get(0)) {
                crossfade(true)
//                transformations(CircleCropTransformation())
//                placeholder(R.drawable.f1_avatar)
            }

            itemView.setOnClickListener {
                itemClick.invoke(article)
            }
        }
    }

    companion object {
        private val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

}