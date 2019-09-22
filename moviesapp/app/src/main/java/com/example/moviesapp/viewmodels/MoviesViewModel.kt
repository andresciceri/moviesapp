package com.example.moviesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.base.API_KEY
import com.example.base.BaseViewModel
import com.example.base.extensions.applyIoScheduler
import com.example.base.models.MoviesResponse
import com.example.base.persistence.Movies
import com.example.base.persistence.MoviesDao
import com.example.moviesapp.controllers.MoviesController
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesViewModel @Inject constructor(
    private val dataSource: MoviesDao,
    private val moviesController: MoviesController
) : BaseViewModel() {

    val movies: MutableLiveData<MoviesResponse> = MutableLiveData()
    val moviesRoom: MutableLiveData<List<Movies>> = MutableLiveData()
    val errorData: MutableLiveData<String> = MutableLiveData()

    private fun insertMovies(movie: Movies): Completable {
        return dataSource.insertMovies(movie)
    }

    private fun getRoomMovies(): Flowable<List<Movies>> {
        return dataSource.getAllMovies()
    }

    private fun getRoomMoviesFilter(filter: String): Flowable<List<Movies>> {
        return dataSource.getMoviesWhereName(filter)
    }

    fun getMovies(isConnction: Boolean) {
        if (isConnction) {
            compositeDisposable.add(
                moviesController.getMovies(API_KEY)
                    .applyIoScheduler()
                    .subscribe({ baseResponse ->
                        if (baseResponse.error == "OK") {
                            movies.value = baseResponse
                            insert()
                        }
                    }, this::handleError)
            )
        } else {
            compositeDisposable.add(
                getRoomMovies()
                    .applyIoScheduler()
                    .subscribe({
                        when (it.isEmpty()) {
                            true -> errorData.value = "Intentelo más tarde"
                            false -> {
                                it.let {
                                    moviesRoom.value = it
                                }
                            }
                        }
                    }, this::handleError)
            )
        }
    }

    fun getMoviesFilter(filter: String, isConnction: Boolean) {
        if (isConnction) {
            compositeDisposable.add(
                moviesController.getMovies(API_KEY, filter)
                    .applyIoScheduler()
                    .subscribe({ baseResponse ->
                        if (baseResponse.error == "OK") {
                            movies.value = baseResponse
                            insert()
                        }
                    }, this::handleError)
            )
        } else {
            compositeDisposable.add(
                getRoomMoviesFilter(filter)
                    .applyIoScheduler()
                    .subscribe({
                        when (it.isEmpty()) {
                            true -> errorData.value = "Intentelo más tarde"
                            false -> {
                                it.let {
                                    moviesRoom.value = it
                                }
                            }
                        }
                    }, this::handleError)
            )
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        errorData.value = error.message
    }

    private fun insert() {
        for (result in movies.value?.results!!) {
            val movie = Movies(
                result.id,
                if (result.deck == null) "" else result.deck,
                if (result.description == null) "" else result.description,
                if (result.name == null) "" else result.name,
                if (result.releaseDate == null) "" else result.releaseDate,
                if (result.runtime == null) "" else result.runtime,
                if (result.image?.superUrl == null) "" else result.image?.superUrl,
                if (result.image?.superUrl == null) "" else result.image?.originalUrl
            )
            compositeDisposable.add(insertMovies(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, this::handleError)
            )
        }
    }
}