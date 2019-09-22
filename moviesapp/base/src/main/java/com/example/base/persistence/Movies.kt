package com.example.base.persistence

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import kotlinx.android.parcel.IgnoredOnParcel
import java.io.Serializable
import java.util.*

@Entity(tableName = "movies")
data class Movies(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int ? = null,
    @ColumnInfo(name = "desk")
    val deck: String ? = null,
    @ColumnInfo(name = "description")
    val description: String ? = null,
    @ColumnInfo(name = "name")
    val name: String ? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String ? = null,
    @ColumnInfo(name = "runtime")
    val runtime: String ? = null,
    @ColumnInfo(name = "super_url")
    val superUrl: String ? = null,
    @ColumnInfo(name = "original_url")
    val originalUrl: String? = null
) : Serializable