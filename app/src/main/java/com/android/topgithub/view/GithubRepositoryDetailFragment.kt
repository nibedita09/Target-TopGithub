package com.android.topgithub.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.topgithub.BR
import com.android.topgithub.R
import com.android.topgithub.databinding.FragmentGithubrepoDetailBinding
import com.android.topgithub.databinding.FragmentGithubrepoListBinding
import com.android.topgithub.model.Repository

/**
 * Created by Nibedita on 01/12/2018.
 */
class GithubRepositoryDetailFragment : BaseFragment(){

    companion object {
        public val TAG : String = GithubRepositoryDetailFragment.javaClass.simpleName
        private const val KEY_REPO : String = "repo"

        fun newInstance(repository : Repository) : GithubRepositoryDetailFragment{
            val fragment = GithubRepositoryDetailFragment()
            fragment.arguments = Bundle().apply { putParcelable(KEY_REPO, repository) }
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding : FragmentGithubrepoDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_githubrepo_detail, container, false)
        val repo = arguments?.getParcelable<Repository>(KEY_REPO)
        binding.setVariable(BR.repository, repo)
        return binding.root
    }


}