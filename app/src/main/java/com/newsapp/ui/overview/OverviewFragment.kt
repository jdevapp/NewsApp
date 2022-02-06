package com.newsapp.ui.overview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.newsapp.R
import com.newsapp.databinding.FragmentOverviewBinding
import com.newsapp.domain.model.Article
import com.newsapp.ui.SharedViewModel
import com.newsapp.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OverviewFragment: Fragment(R.layout.fragment_overview), ArticlesListener {
    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var adpt: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        adpt = ArticlesAdapter(this)

        binding.articleRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adpt
        }

        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing = false
            binding.firstArticleProgress.show()
            viewModel.refresh()
        }

        //Third row : This web embbed
        val unencodedHtml = getString(R.string.web_embbed_html) // used by WebView
        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
        binding.embbedWebView.apply {
            loadData(encodedHtml,"text/html", "base64")
        }

        launchAndRepeatWithViewLifecycle {
            observeViewModel()
        }
    }

    private fun CoroutineScope.observeViewModel() {
        launch  {
            viewModel.articles.collect { articles ->
                Log.d("NEWSAPP_LOG", "articles.size: ${ articles.size}")
                adpt.submitList(articles)
            }
        }
        launch  {
            viewModel.firstArticle.collect { article ->
                if(article != null){
                    binding.firstArticleProgress.hide()

                    Glide
                        .with(this@OverviewFragment)
                        .load(article.urlToImage)
                        .fallback(ColorDrawable(Color.GRAY))
                        .centerCrop()
                        .into(binding.firstArticleImg)

                    binding.firstArticleCard.setOnClickListener {
                        navigateToDetails(0)
                    }
                }
            }
        }
        launch  {
            viewModel.secondArticle.collect { article ->
                if(article != null){
                    Glide
                        .with(this@OverviewFragment)
                        .load(article.urlToImage)
                        .fallback(ColorDrawable(Color.GRAY))
                        .centerCrop()
                        .into(binding.secondArticleImg)

                    binding.secondArticleCard.setOnClickListener {
                        navigateToDetails(1)
                    }
                }
            }
        }
        launch  {
            viewModel.thirdArticle.collect { article ->
                if(article != null){
                    Glide
                        .with(this@OverviewFragment)
                        .load(article.urlToImage)
                        .fallback(ColorDrawable(Color.GRAY))
                        .centerCrop()
                        .into(binding.thirdArticleImg)

                    binding.thirdArticleCard.setOnClickListener {
                        navigateToDetails(2)
                    }
                }
            }
        }
    }

    // articles from Recyclerview
    override fun onArticleClicked(article: Article, position: Int) {
        navigateToDetails(position)
    }

    private fun navigateToDetails(position: Int){
        val bundle = bundleOf(
            "label" to "Details",
            "position" to position
        )
        findNavController().navigate(R.id.action_overviewFragment_to_detailsFragment, bundle)
    }
}