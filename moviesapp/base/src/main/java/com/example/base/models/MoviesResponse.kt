package com.example.base.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class MoviesResponse(
    @SerializedName("number_of_total_results")
    val numberOfTotalResults: Int? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("number_of_page_results")
    val numberOfPageResults: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("results")
    val results: List<ResultsItem>? = null,
    @SerializedName("version")
    val version: String? = null
)

data class ResultsItem(
    @SerializedName("image")
    val image: Image? = null,
    @SerializedName("studios")
    val studios: List<StudiosItem>? = null,
    @SerializedName("site_detail_url")
    val siteDetailUrl: String? = null,
    @SerializedName("deck")
    val deck: String? = null,
    @SerializedName("rating")
    val rating: String? = null,
    @SerializedName("box_office_revenue")
    val boxOfficeRevenue: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("runtime")
    val runtime: String? = null,
    @SerializedName("writers")
    val writers: List<WritersItem>? = null,
    @SerializedName("distributor")
    val distributor: String? = null,
    @SerializedName("producers")
    val producers: List<ProducersItem>? = null,
    @SerializedName("api_detail_url")
    val apiDetailUrl: String? = null,
    @SerializedName("date_added")
    val dateAdded: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("total_revenue")
    val totalRevenue: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("budget")
    val budget: String? = null,
    @SerializedName("date_last_updated")
    val dateLastUpdated: String? = null,
    @SerializedName("has_staff_review")
    val hasStaffReview: String? = null
) : Serializable

data class Image(
    @SerializedName("icon_url")
    val iconUrl: String? = null,
    @SerializedName("screen_large_url")
    val screenLargeUrl: String? = null,
    @SerializedName("thumb_url")
    val thumbUrl: String? = null,
    @SerializedName("tiny_url")
    val tinyUrl: String? = null,
    @SerializedName("small_url")
    val smallUrl: String? = null,
    @SerializedName("super_url")
    val superUrl: String? = null,
    @SerializedName("original_url")
    val originalUrl: String? = null,
    @SerializedName("screen_url")
    val screenUrl: String? = null,
    @SerializedName("medium_url")
    val mediumUrl: String? = null,
    @SerializedName("image_tags")
    val imageTags: String? = null
)

data class ProducersItem(
    @SerializedName("api_detail_url")
    val apiDetailUrl: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("site_detail_url")
    val siteDetailUrl: String? = null
)

data class StudiosItem(
    @SerializedName("api_detail_url")
    val apiDetailUrl: String? = null,
    @SerializedName("site_detail_url")
    val siteDetailUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null
)

data class WritersItem(
    @SerializedName("api_detail_url")
    val apiDetailUrl: String? = null,
    @SerializedName("site_detail_url")
    val siteDetailUrl: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null
)


data class Movies(
    @SerializedName("id")
    val id: Int,
    @SerializedName("desk")
    val deck: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: String,
    @SerializedName("super_url")
    val superUrl: String,
    @SerializedName("original_url")
    val originalUrl: String? = null
)