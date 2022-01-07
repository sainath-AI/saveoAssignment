package com.masai.sainath.saveoproject.ui.viewHolders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.sainath.saveoproject.R
import com.masai.sainath.saveoproject.data.model.MovieModel
import com.masai.sainath.saveoproject.ui.interfaces.OnItemClickListener

class MovieViewHolder(itemView: View, private val listener: OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    fun setDataFromHorizontalScroll(movieModel: MovieModel) {
        itemView.apply {
            val mIvImage = findViewById<ImageView>(R.id.ivHorizontalImage)

            Glide.with(mIvImage).load(movieModel.image?.original).into(mIvImage)

            mIvImage.setOnClickListener {
                listener.onItemClicked(movieModel, mIvImage)
            }
        }
    }

    fun setDataFromVerticalScroll(movieModel: MovieModel) {
        itemView.apply {
            val mIvImage = findViewById<ImageView>(R.id.ivVerticalImage)

            Glide.with(mIvImage).load(movieModel.image?.medium).into(mIvImage)

            mIvImage.setOnClickListener {
                listener.onItemClicked(movieModel, mIvImage)
            }
        }
    }
}