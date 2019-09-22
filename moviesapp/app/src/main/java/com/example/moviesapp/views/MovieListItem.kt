package com.example.moviesapp.views

import android.annotation.SuppressLint
import com.example.base.models.ResultsItem
import com.example.moviesapp.R
import com.example.moviesapp.activities.DETAILS_FRAGMENT_TAG
import com.example.moviesapp.activities.HomeActivity
import com.example.moviesapp.fragments.DetailsMoviesFragment
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_movie.*
import java.text.SimpleDateFormat

class MovieListItem(private val movies: ResultsItem, private val activity: HomeActivity) : Item() {

    @SuppressLint("SimpleDateFormat")
    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener {
            val detailsFragment = DetailsMoviesFragment.newInstance(movies)
            activity.showDetailsFragment(true)
            activity.supportFragmentManager.beginTransaction().add(R.id.overlayFragmentContainer, detailsFragment, DETAILS_FRAGMENT_TAG).commitNow()
        }

        Picasso.get().load(movies.image?.originalUrl).into(viewHolder.imageView_movieImage)

        viewHolder.textView_nameMovie.text = movies.name
        viewHolder.textView_nameMovie.isSelected = true

        val releaseDate = SimpleDateFormat("yyyy").format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(movies?.releaseDate?.replace(" ", "T")))
        viewHolder.textView_ageMovie.text = releaseDate
    }

    override fun getLayout(): Int = R.layout.item_movie
}