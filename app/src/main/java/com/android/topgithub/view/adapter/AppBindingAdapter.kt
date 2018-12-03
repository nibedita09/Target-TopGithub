package com.android.topgithub.view.adapter

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.android.topgithub.R
import com.squareup.picasso.Picasso

/**
 * Created by Nibedita on 02/12/2018.
 */
class AppBindingAdapter {
    companion object {
        @JvmStatic @BindingAdapter("imageUrl")
        fun ImageView.loadImage(imageUrl: String) {
            Picasso.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(this)
        }

        @JvmStatic @BindingAdapter("visibleGone")
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}