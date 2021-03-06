package com.newsapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.newsapp.databinding.FragmentDetailsBinding
import com.newsapp.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {
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

        val position = arguments?.getInt("position")
        if(position != null){
            binding.viewpager.setCurrentItem(position, false)
        }

    }
}