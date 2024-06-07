package com.example.advweek4.view

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.advweek4.R
import com.example.advweek4.databinding.StudentListItemBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url:String) {
    val picasso = Picasso.Builder(imageView.context)
    picasso.listener { picasso, uri, exception ->
        exception.printStackTrace()
    }
    picasso.build().load(url).into(imageView, object : Callback {
        override fun onSuccess() {
            (imageView.parent as? View)?.let { parent ->
                parent.findViewById<View>(R.id.progressImage)?.visibility = View.INVISIBLE
                imageView.visibility = View.VISIBLE
            }
        }

        override fun onError(e: Exception?) {
            Log.e("picasso_error", e.toString())
        }
    })
}

