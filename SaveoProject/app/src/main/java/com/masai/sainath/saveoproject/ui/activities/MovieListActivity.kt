package com.masai.sainath.saveoproject.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.saveoproject.data.model.MovieModel
import com.masai.sainath.saveoproject.viewModel.MovieViewModel
import com.masai.sainath.saveoproject.ui.adapters.HorizontalScrollAdapter
import com.masai.sainath.saveoproject.ui.adapters.VerticalScrollAdapter
import com.masai.sainath.saveoproject.ui.interfaces.OnItemClickListener
import com.masai.sainath.saveoproject.databinding.ActivityMoveListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var binding: ActivityMoveListBinding

    lateinit var horizontalScrollAdapter: HorizontalScrollAdapter

    lateinit var verticalScrollAdapter: VerticalScrollAdapter

    private var verticalScrollList = arrayListOf<MovieModel>()

    private var horizontalScrollList = arrayListOf<MovieModel>()

    val movieViewModel by viewModels<MovieViewModel>()

    private var pageNumber = 1

    lateinit var gridLayoutManager: GridLayoutManager

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shimmerFrameLayoutVertical.startShimmer()

        setHorizontalRecyclerView()

        setVerticalRecyclerView()

        movieViewModel.getMovieItemList().observe(this, Observer {
            stopShimmer()

            verticalScrollList.clear()
            horizontalScrollList.clear()

            //adding the first 7 movies to the top carousel
            for (i in 0..6) {
                horizontalScrollList.add(it[i])
            }

            verticalScrollList.addAll(it)

            horizontalScrollAdapter.notifyDataSetChanged()
            verticalScrollAdapter.notifyDataSetChanged()
        })

        implementPagination()
    }

    private fun setVerticalRecyclerView() {
        verticalScrollAdapter = VerticalScrollAdapter(verticalScrollList, this)
        gridLayoutManager = GridLayoutManager(this@MovieListActivity, 3)
        binding.rvRecyclerView.apply {
            adapter = verticalScrollAdapter
            layoutManager = gridLayoutManager
        }
    }

    private fun setHorizontalRecyclerView() {
        horizontalScrollAdapter = HorizontalScrollAdapter(horizontalScrollList, this)
        binding.rvHorizontalRecyclerView.apply {
            adapter = horizontalScrollAdapter
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    /**
     * when a specific item is clicked, this will be implemented
     */
    override fun onItemClicked(movieModel: MovieModel, mIvImage: ImageView) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("showsModel", movieModel)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            mIvImage,
            ViewCompat.getTransitionName(mIvImage)!!
        )

        startActivity(intent, options.toBundle())
    }

    /**
     * pagination i.e., list gets loaded by calling the api again and again as we scroll to the
     * end of the list
     */
    private fun implementPagination() {
        var loading = true
        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        binding.rvRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = gridLayoutManager.childCount
                    totalItemCount = gridLayoutManager.itemCount
                    pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            pageNumber++
                            loading = false
                            movieViewModel.getMovieItemListForPagination(pageNumber)
                                .observe(this@MovieListActivity, Observer {
                                    verticalScrollList.addAll(it)
                                    verticalScrollAdapter.notifyDataSetChanged()
                                })
                            loading = true
                        }
                    }
                }
            }
        })
    }

    /**
     * stops shimmering and makes other views visible
     */
    private fun stopShimmer() {
        binding.apply {
            shimmerFrameLayoutVertical.stopShimmer()
            shimmerFrameLayoutVertical.visibility = View.INVISIBLE
            rvRecyclerView.visibility = View.VISIBLE
            tvNowShowing.visibility = View.VISIBLE
            rvHorizontalRecyclerView.visibility = View.VISIBLE
        }
    }
}