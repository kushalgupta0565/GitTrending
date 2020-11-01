package com.kushal.gittrending.pagination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kushal.gittrending.R
import com.kushal.gittrending.model.git_trending.Item

class TrendingRepoListAdapter :
    PagedListAdapter<Item, TrendingRepoListAdapter.TrendingRepoItemViewHoldet>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TrendingRepoItemViewHoldet(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.trending_repo_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: TrendingRepoItemViewHoldet, position: Int) {
        val data = getItem(position)
        holder.textViewTitle.text = data?.fullName
        holder.textViewDesc.text = data?.description
        holder.textViewLanguage.text = data?.language.toString()
        holder.textViewStars.text = data?.stargazersCount.toString()
        holder.textViewForks.text = data?.forksCount.toString()

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("repo_data" to data)
            it.findNavController().navigate(R.id.action_frag_git_details, bundle)
        }
    }

    class TrendingRepoItemViewHoldet(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewDesc = itemView.findViewById<TextView>(R.id.textViewDesc)
        val textViewLanguage = itemView.findViewById<TextView>(R.id.textViewLanguage)
        val textViewStars = itemView.findViewById<TextView>(R.id.textViewStars)
        val textViewForks = itemView.findViewById<TextView>(R.id.textViewForks)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.fullName == newItem.fullName
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.fullName == newItem.fullName
            }
        }
    }
}