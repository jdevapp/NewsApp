package com.newsapp.ui.details

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.R
import com.newsapp.databinding.ItemArticleDetailsBinding
import com.newsapp.domain.model.Article

class ArticlesDetailsAdapter(

): RecyclerView.Adapter<ArticlesDetailsAdapter.ViewHolder>() {

    private val data: AsyncListDiffer<Article> = AsyncListDiffer(this, object:
        DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.equals(newItem)
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.equals(newItem)
        }
    })

    init {
        setHasStableIds(true)
    }

    inner class ViewHolder(val binding: ItemArticleDetailsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article) {
            binding.article = article

            Glide
                .with(binding.root)
                .load(article.urlToImage)
                .fallback(ColorDrawable(Color.GRAY))
                .centerCrop()
                .into(binding.articleImg)


            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_article_details, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.currentList[position])
    }
    override fun getItemId(position: Int): Long {
        return data.currentList[position].hashCode().toLong()
    }
    override fun getItemCount(): Int {
        return data.currentList.size
    }
    fun submitList(articles: List<Article>) {
        if (articles.isEmpty()) {
            data.submitList(emptyList())
            return
        }
        data.submitList(articles)
    }
}
