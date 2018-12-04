package com.android.topgithub.view


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.topgithub.R
import com.android.topgithub.TopGithubApplication
import com.android.topgithub.databinding.FragmentGithubrepoListBinding
import com.android.topgithub.model.Repository
import com.android.topgithub.view.adapter.GithubRepoListAdapter
import com.android.topgithub.viewmodel.GithubRepositoryListViewModel
import com.android.topgithub.viewmodel.GithubRepositoryListViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_githubrepo_list.*
import java.net.UnknownHostException
import javax.inject.Inject


class GithubRepositoryListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory : GithubRepositoryListViewModelFactory
    private lateinit var viewModel : GithubRepositoryListViewModel

    private var githubRepoAdapter : GithubRepoListAdapter? = null
    private var activityContext : Context? = null
    private lateinit var disposable : Disposable

    companion object {
        val TAG : String = GithubRepositoryListFragment::class.java.simpleName
        fun newInstance() : GithubRepositoryListFragment{
            val fragment = GithubRepositoryListFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TopGithubApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubRepositoryListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding : FragmentGithubrepoListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_githubrepo_list, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.layoutManager = LinearLayoutManager(activityContext)
        listView.addItemDecoration(DividerItemDecoration(activityContext, DividerItemDecoration.VERTICAL))
        loadData()
    }

    private fun loadData(){
        viewModel.setLoading(true)
        disposable = viewModel.getReposObservable().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
    }

    private fun handleResponse(repoList : List<Repository>){
        viewModel.setLoading(false)
        if(githubRepoAdapter != null) {
            githubRepoAdapter?.setItems(repoList)
        } else {
            githubRepoAdapter = GithubRepoListAdapter(repoList, ListItemCallback())
            listView.adapter = githubRepoAdapter
        }
    }

    private fun handleError(error : Throwable){
        viewModel.setLoading(false)

        if(error is UnknownHostException)
            super.showAlertDialog(getString(R.string.error_title), getString(R.string.error_msg_network))
        else {
            super.showAlertDialog(getString(R.string.error_title), getString(R.string.error_msg_technical))
        }
    }

    inner class ListItemCallback : ItemClickCallback<Repository> {
        override fun onClick(t: Repository) {
            navigateTo(GithubRepositoryDetailFragment.newInstance(t), GithubRepositoryDetailFragment.TAG)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activityContext  = context
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}