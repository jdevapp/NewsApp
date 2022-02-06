package com.newsapp.ui.overview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.newsapp.R
import com.newsapp.databinding.ItemArticleBinding
import com.newsapp.domain.model.Article

class ArticlesAdapter(
    private val listener: ArticlesListener
): RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

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

    inner class ViewHolder(val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article, position: Int, listener: ArticlesListener) {
            binding.article = article
            binding.position = position
            binding.listener = listener

            Glide
                .with(binding.root)
                .load(article.urlToImage)
                .fallback(ColorDrawable(Color.GRAY))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.articleImg)


            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.currentList[position], position, listener)
    }
    override fun getItemId(position: Int): Long {
        return data.currentList[position].hashCode().toLong()
    }
    override fun getItemCount(): Int {
        return data.currentList.size
    }
    fun submitList(stations: List<Article>) {
        if (stations.isEmpty()) {
            data.submitList(emptyList())
            return
        }
        data.submitList(stations)
    }
}
