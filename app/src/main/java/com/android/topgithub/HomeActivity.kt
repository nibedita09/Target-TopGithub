package com.android.topgithub

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.android.topgithub.view.FragmentCallback
import com.android.topgithub.view.GithubRepositoryListFragment

class HomeActivity : AppCompatActivity(), FragmentCallback {

    private lateinit var dialog : AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, GithubRepositoryListFragment.newInstance(), GithubRepositoryListFragment.TAG)
                    .commit()

    }

    override fun showAlertDialog(title: String, msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            finish()
        }
        dialog = builder.create()
        dialog.show()
    }
}
