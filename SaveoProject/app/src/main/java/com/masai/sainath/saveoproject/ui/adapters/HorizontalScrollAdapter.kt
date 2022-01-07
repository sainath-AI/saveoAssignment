package com.masai.sainath.saveoproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.saveoproject.data.model.MovieModel
import com.masai.sainath.saveoproject.ui.interfaces.OnItemClickListener
import com.masai.sainath.saveoproject.ui.viewHolders.MovieViewHolder
import com.masai.sainath.saveoproject.R

class HorizontalScrollAdapter(
    private val movieModelList: List<MovieModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_scroll_item_layout, parent, false)
        return MovieViewHolder(view, listener)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val showsModel = movieModelList[position]
        holder.setDataFromHorizontalScroll(showsModel)
    }

    override fun getItemCount(): Int {
        return movieModelList.size
    }
}