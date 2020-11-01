package com.kushal.gittrending.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kushal.gittrending.databinding.FragmentTrendingListBinding
import com.kushal.gittrending.pagination.adapter.TrendingRepoListAdapter
import com.kushal.gittrending.view_model.ItemViewModel

class TrendingListFragment : Fragment() {

    private lateinit var binding: FragmentTrendingListBinding
    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE
        val adapter = TrendingRepoListAdapter()
        binding.rvTrendingList.adapter = adapter

        itemViewModel.itemPagedList!!.observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(it)
        })
    }
}