package com.kushal.gittrending.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        binding.tvKey1.text = "Full Name"
        binding.tvValue1.text = "${repo_data?.fullName}"

        binding.tvKey2.text = "Description"
        binding.tvValue2.text = "${repo_data?.description}"

        binding.tvKey3.text = "Repo URL"
        binding.tvValue3.text = "${Html.fromHtml(repo_data?.htmlUrl)}"
        binding.tvValue3.setLinksClickable(true);
        binding.tvValue3.setAutoLinkMask(Linkify.WEB_URLS);

        binding.tvKey4.text = "Forks"
        binding.tvValue4.text = "${repo_data?.forksCount}"

        binding.tvKey5.text = "Star"
        binding.tvValue5.text = "${repo_data?.stargazersCount}"

        binding.tvKey6.text = "Watchers"
        binding.tvValue6.text = "${repo_data?.watchersCount}"

        binding.tvKey7.text = "Open Issues"
        binding.tvValue7.text = "${repo_data?.openIssuesCount}"

        binding.tvKey7.text = "Default Branch"
        binding.tvValue7.text = "${repo_data?.defaultBranch}"


    }
}