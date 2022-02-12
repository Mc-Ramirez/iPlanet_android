package com.utad.iplanet

import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.http.Url

fun ImageView.imageURL(imageUrl: String){
    Picasso.get().load(imageUrl).into(this)
}