package com.kushal.gittrending.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kushal.gittrending.R
import com.kushal.gittrending.databinding.FragmentRepoDetailsBinding
import com.kushal.gittrending.model.git_trending.Item

class RepoDetailsFragment: Fragment() {

    private lateinit var binding: FragmentRepoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo_data = arguments?.getSerializable("repo_data") as Item?

        loadImageFromUrl(repo_data?.owner?.avatarUrl, binding.imageViewCollapsing)
        binding.textViewStars.text = "${repo_data?.stargazersCount}"
        binding.textViewForks.text = "${repo_data?.forksCount}"
        binding.textViewWatchers.text = "${repo_data?.watchersCount}"
        binding.textViewOpenIssues.text = "${repo_data?.openIssuesCount}"
        binding.textViewLanguage.text = "${repo_data?.language}"
        binding.textViewLicense.text = "${repo_data?.license?.name}"
        binding.textViewLastUpdated.text = "${repo_data?.updatedAt}"
        binding.textViewFullName.text = "${repo_data?.fullName}"
        binding.textViewDesc.text = "${repo_data?.description}"
    }

    fun loadImageFromUrl(url: String?, imageView: ImageView) {
        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_github_octocat)
            .into(imageView)
    }
}