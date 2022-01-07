package com.masai.sainath.saveoproject.ui.interfaces

import android.widget.ImageView
import com.masai.sainath.saveoproject.data.model.MovieModel

interface OnItemClickListener {

    fun onItemClicked(movieModel: MovieModel, mIvImage: ImageView)

}