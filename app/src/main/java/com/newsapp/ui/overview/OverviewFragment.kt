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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.newsapp.R
import com.newsapp.databinding.FragmentOverviewBinding
import com.newsapp.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OverviewFragment: Fragment(R.layout.fragment_overview) {
    private lateinit var binding: FragmentOverviewBinding
    private val viewModel: ArticlesViewModel by viewModels()

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
        binding.firstArticleCard.setOnClickListener {
            val bundle = bundleOf(
                "label" to "label",
                "articleId" to "articleId"
            )
            findNavController().navigate(R.id.action_overviewFragment_to_detailsFragment, bundle)
        }

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
            }
        }
        launch  {
            viewModel.firstArticle.collect { article ->
                if(article != null){
                    Glide
                        .with(this@OverviewFragment)
                        .load(article.urlToImage)
                        .fallback(ColorDrawable(Color.GRAY))
                        .centerCrop()
                        .transition(withCrossFade())
                        .into(binding.firstArticleImg)

                    Glide
                        .with(this@OverviewFragment)
                        .load(article.urlToImage)
                        .fallback(ColorDrawable(Color.GRAY))
                        .into(binding.secondArticleImg)

                    Glide
                        .with(this@OverviewFragment)
                        .load(article.urlToImage)
                        .fallback(ColorDrawable(Color.GRAY))
                        .into(binding.thirdArticleImg)
                }
            }
        }
    }
}