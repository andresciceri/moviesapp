package com.example.moviesapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LayoutAnimationController
import android.view.animation.TranslateAnimation
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.base.BaseActivity
import com.example.base.models.MoviesResponse
import com.example.base.models.ResultsItem
import com.example.base.persistence.Movies
import com.example.base.persistence.MoviesDatabase
import com.example.moviesapp.GridSpaces
import com.example.moviesapp.R
import com.example.moviesapp.viewmodels.MoviesViewModel
import com.example.moviesapp.views.MovieListItem
import com.example.moviesapp.views.MovieRoomListItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

const val DETAILS_FRAGMENT_TAG = "details_fragment_tag"

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MoviesViewModel

    private val moviesAdapter = GroupAdapter<ViewHolder>()

    @SuppressLint("MissingPermission")
    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviesDatabase.getInstance(this)

        subscribeToObservables()
        initListeners()
        setupBlurViews()
        viewModel.getMovies(isOnline())
    }

    private fun subscribeToObservables() {
        viewModel.movies.observe(this, Observer<MoviesResponse> {
            handleSuccess(it)
        })

        viewModel.moviesRoom.observe(this, Observer<List<Movies>> {
            handleSuccessRoom(it)
        })

        viewModel.errorData.observe(this, Observer<String> {
            onShowError(it)
        })
    }

    private fun initListeners() {
        var timer = Timer()
        val delay: Long = 1500
        editText_searchMovie.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(
                    object : TimerTask() {
                        override fun run() {
                            viewModel.getMoviesFilter("name:${p0.toString()}", isOnline())
                        }
                    },
                    delay
                )
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        imageView_searchButton.setOnClickListener {
            when (linearLayout_search.visibility == View.GONE) {
                true -> {
                    showSearchView(true)
                    linearLayout_search.visibility = View.VISIBLE
                    editText_searchMovie.isFocusable = true
                }
                false -> {
                    showSearchView(false)
                    linearLayout_search.visibility = View.GONE
                    editText_searchMovie.setText("")
                    editText_searchMovie.clearFocus()
                }
            }
        }
    }

    private fun setupBlurViews() {
        appBarBlurLayout.viewBehind = viewToBlur
    }

    private fun handleSuccess(response: MoviesResponse) {
        if (moviesAdapter.itemCount > 0) {
            moviesAdapter.clear()
            for (movie in response.results!!) {
                moviesAdapter.add(MovieListItem(movie, this))
            }
        } else {
            setupRecyclerView(response.results)
        }
    }

    private fun handleSuccessRoom(response: List<Movies>) {
        if (moviesAdapter.itemCount > 0) {
            moviesAdapter.clear()
            for (movie in response) {
                moviesAdapter.add(MovieRoomListItem(movie, this))
            }
        } else {
            setupRoomRecyclerView(response)
        }
    }

    private fun setupRecyclerView(movies: List<ResultsItem>?) {
        recyclerView_listMovies.apply {
            setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING)
            addItemDecoration(GridSpaces(resources.getDimension(R.dimen.grid_top_padding).toInt()))
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        for (movie in movies!!) {
            moviesAdapter.add(MovieListItem(movie, this))
        }
    }

    private fun setupRoomRecyclerView(movies: List<Movies>?) {
        recyclerView_listMovies.apply {
            setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING)
            addItemDecoration(GridSpaces(resources.getDimension(R.dimen.grid_top_padding).toInt()))
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        for (movie in movies!!) {
            moviesAdapter.add(MovieRoomListItem(movie, this))
        }
    }

    private fun showSearchView(show: Boolean) {
        val set = AnimationSet(true)
        val animation: Animation? = if (show) {
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
            )
        } else {
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
            )
        }
        animation?.duration = 500
        set.addAnimation(animation)
        val controller = LayoutAnimationController(set, 0.25f)

        linearLayout_search.layoutAnimation = controller
        linearLayout_search.startAnimation(animation)
    }

    fun showDetailsFragment(show: Boolean) {
        val set = AnimationSet(true)
        val animation: Animation? = if (show) {
            overlayFragmentContainer.visibility = View.VISIBLE
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
            )
        } else {
            overlayFragmentContainer.visibility = View.GONE
            TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f
            )
        }
        animation?.duration = 750
        set.addAnimation(animation)
        val controller = LayoutAnimationController(set, 0.25f)

        overlayFragmentContainer.layoutAnimation = controller
        overlayFragmentContainer.startAnimation(animation)
    }

    override fun onBackPressed() {
        var exitApp = true
        val fragment  = supportFragmentManager.findFragmentByTag(DETAILS_FRAGMENT_TAG)
        if (fragment != null) {
            exitApp = false
            showDetailsFragment(false)
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }

        if (exitApp) {
            super.onBackPressed()
        }
    }
}
