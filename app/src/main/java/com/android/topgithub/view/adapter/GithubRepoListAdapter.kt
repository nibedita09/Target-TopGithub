package com.android.topgithub.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.topgithub.BR
import com.android.topgithub.databinding.GithubrepoListItemBinding
import com.android.topgithub.model.Repository
import com.android.topgithub.view.ItemClickCallback

/**
 * Created by Nibedita on 02/12/2018.
 */
class GithubRepoListAdapter(var data : List<Repository>, val itemCallback : ItemClickCallback<Repository>) : RecyclerView.Adapter<GithubRepoListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val binding = GithubrepoListItemBinding.inflate(inflater);
        return ViewHolder(binding)
    }

    fun setItems(items: List<*>) {
        data = items as List<Repository>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding : GithubrepoListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Repository){
            with(binding) {
                setVariable(BR.repo, item)
                callback = itemCallback
                executePendingBindings()
            }
        }
    }
}