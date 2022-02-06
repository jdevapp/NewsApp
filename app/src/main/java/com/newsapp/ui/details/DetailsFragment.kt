package com.newsapp.ui.details

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.newsapp.R
import com.newsapp.databinding.FragmentDetailsBinding
import com.newsapp.domain.model.Article
import com.newsapp.ui.SharedViewModel
import com.newsapp.ui.overview.ArticlesAdapter
import com.newsapp.ui.overview.ArticlesListener
import com.newsapp.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment(R.layout.fragment_details), ArticlesListener {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var adpt: ArticlesDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

        adpt = ArticlesDetailsAdapter()
        adpt.submitList(viewModel.articles.value)

        binding.viewpager.adapter = adpt

    }

    override fun onArticleClicked(article: Article) {
        TODO("Not yet implemented")
    }
}