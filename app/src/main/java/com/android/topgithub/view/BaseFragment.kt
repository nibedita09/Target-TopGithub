package com.android.topgithub.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.android.topgithub.R

open class BaseFragment : Fragment() {
    var fragmentCallback : FragmentCallback? = null

    fun navigateTo(t: Fragment, tag : String) {
        activity!!.supportFragmentManager
                .beginTransaction()
                .add(R.id.container, t, tag)
                .addToBackStack(null)
                .commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true;
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is FragmentCallback) fragmentCallback = context
    }

    fun showAlertDialog(title : String, msg : String){
        fragmentCallback!!.showAlertDialog(title, msg)
    }
}