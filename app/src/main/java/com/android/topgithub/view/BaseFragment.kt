package com.android.topgithub.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.android.topgithub.R

open class BaseFragment : Fragment() {
    fun navigateTo(t: Fragment, tag : String, isAddedToBackStack : Boolean) {
        this.activity!!.supportFragmentManager.inTransaction {
            add(R.id.container, t)
            if(isAddedToBackStack) addToBackStack(tag)
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}