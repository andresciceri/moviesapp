package com.example.base.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: Movies): Completable

    @Update
    fun updateMovies(vararg movie: Movies)

    @Query("DELETE FROM Movies")
    fun deleteAllMovies()

    @Query("SELECT * FROM Movies")
    fun getAllMovies(): Flowable<List<Movies>>

    @Query("SELECT * FROM Movies WHERE name=:name")
    fun getMoviesWhereName(name: String): Flowable<List<Movies>>
}