package ru.kozirfm.f1news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_article.view.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Article

class ArticlesRecyclerViewAdapter :
    RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ArticlesViewHolder>() {

    var articles: List<Article> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        return ArticlesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.count()
    }

    inner class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) = with(itemView) {
            articleTitleTextView.text = article.title
            articleDateTextView.text = article.date
            articleTextTextView.text = article.text

            itemView.setOnClickListener {
                if (articleTextTextView.visibility == View.GONE) {
                    articleTextTextView.visibility = View.VISIBLE
                    articleArrowDirectionImageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                } else {
                    articleTextTextView.visibility = View.GONE
                    articleArrowDirectionImageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                }
            }
        }
    }
}
