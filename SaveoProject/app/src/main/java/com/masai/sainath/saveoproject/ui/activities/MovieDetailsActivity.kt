package com.masai.sainath.saveoproject.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.masai.sainath.saveoproject.data.model.MovieModel
import com.masai.sainath.saveoproject.databinding.ActivityMovieDetailsBinding
import org.jsoup.Jsoup


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding

    lateinit var movieModel: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent != null && intent.extras != null) {

            //getting the model class object from the previous activity using serializable
            movieModel = intent.getSerializableExtra("showsModel") as MovieModel

            setDataInViews()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataInViews() {
        binding.apply {

            Glide.with(ivMovieImage).load(movieModel.image?.original).into(ivMovieImage)

            tvMovieName.text = movieModel.name.toString()

            //converting html text to normal text using jsoup library
            val textFromHtml: String = Jsoup.parse(movieModel.summary).text()
            tvSummaryDescription.text = textFromHtml

            //getting list of genres and depending on the list setting them into textViews
            val genreList = movieModel.genres
            if (!genreList?.isEmpty()!!) {
                var count: Int = genreList.size
                if (count > 0 && genreList[0] != null) {
                    tvGenre1.text = genreList[0].toString()
                    tvGenre1.visibility = View.VISIBLE
                    count--;
                }
                if (count > 0 && genreList[1] != null) {
                    tvGenre2.text = genreList[1].toString()
                    tvGenre2.visibility = View.VISIBLE
                }
            }

            //converting the rating out of 10 into rating out of 5 and setting it in rating bar
            if (movieModel.rating?.average != null) {
                rbRatingBar.rating = movieModel.rating!!.average.toString().toFloat() / 2
                val rating = movieModel.rating!!.average.toString().toFloat() / 2
                val ratingString = String.format("%.1f", rating)
                tvRating.text = ratingString
            }

            tvPremiere.text = "${movieModel.runtime} mins | Premiered on ${movieModel.premiered}"
            tvLanguage.text = movieModel.language.toString()
        }
    }
}