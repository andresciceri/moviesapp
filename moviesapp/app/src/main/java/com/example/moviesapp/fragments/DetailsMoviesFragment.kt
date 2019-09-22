package com.example.moviesapp.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseFragment
import com.example.base.models.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_movie.view.imageView_coverImage
import kotlinx.android.synthetic.main.fragment_details_movies.view.*
import java.text.SimpleDateFormat
import android.text.Spanned
import com.example.base.persistence.Movies
import com.example.moviesapp.R


private const val MOVIE_ITEM = "movie_item"
private const val ROOM_MOVIE_ITEM = "room_movie_item"

class DetailsMoviesFragment : BaseFragment() {

    private lateinit var movieItem: ResultsItem
    private lateinit var roomMovieItem: Movies
    private var isDataRoom: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            when (it.containsKey(MOVIE_ITEM)) {
                true -> {
                    isDataRoom = false
                    movieItem = it.getSerializable(MOVIE_ITEM) as ResultsItem
                }
                false -> {
                    isDataRoom = true
                    roomMovieItem = it.getSerializable(ROOM_MOVIE_ITEM) as Movies
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_details_movies, container, false)
        val viewToBlur = activity?.findViewById<View>(R.id.viewToBlur) as ViewGroup

        setupBlurViews(rootView, viewToBlur)
        initView(rootView)

        return rootView
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initView(rootView: View) {
        if (!isDataRoom) {
            Picasso.get().load(movieItem.image?.superUrl).into(rootView.imageView_coverImage)

            val releaseDate = SimpleDateFormat("yyyy").format(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(
                    movieItem.releaseDate?.replace(
                        " ",
                        "T"
                    )
                )
            )

            rootView.textView_name.isSelected = true
            rootView.textView_name.text = movieItem.name

            rootView.textView_date.text = releaseDate

            rootView.textView_timeDuration.text = "${movieItem.runtime} min."

            rootView.textView_description.movementMethod = ScrollingMovementMethod()
            rootView.textView_description.text =
                if (movieItem.description != null) fromHtml(movieItem.description) else movieItem.deck
        } else {
            Picasso.get().load(roomMovieItem.superUrl).into(rootView.imageView_coverImage)

            val releaseDate = SimpleDateFormat("yyyy").format(
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(
                    roomMovieItem.releaseDate?.replace(
                        " ",
                        "T"
                    )
                )
            )

            rootView.textView_name.isSelected = true
            rootView.textView_name.text = roomMovieItem.name

            rootView.textView_date.text = releaseDate

            rootView.textView_timeDuration.text = "${roomMovieItem.runtime} min."

            rootView.textView_description.movementMethod = ScrollingMovementMethod()
            rootView.textView_description.text =
                if (roomMovieItem.description != "") fromHtml(roomMovieItem.description) else roomMovieItem.deck
        }
    }

    private fun setupBlurViews(rootView: View, viewToBlur: ViewGroup) {
        rootView.fullScreenBlur.viewBehind = viewToBlur
        rootView.fullScreenBlur.updateForMilliSeconds(100L)
    }

    private fun fromHtml(html: String?): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(movie: ResultsItem) =
            DetailsMoviesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MOVIE_ITEM, movie)
                }
            }

        @JvmStatic
        fun newInstance(movie: Movies) =
            DetailsMoviesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ROOM_MOVIE_ITEM, movie)
                }
            }
    }
}