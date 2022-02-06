package com.newsapp.ui.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        binding.detailsButton.setOnClickListener {
            val bundle = bundleOf(
                "label" to "label",
                "articleId" to "articleId"
            )
            findNavController().navigate(R.id.action_overviewFragment_to_detailsFragment, bundle)
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

    }


}