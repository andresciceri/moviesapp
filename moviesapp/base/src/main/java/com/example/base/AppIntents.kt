package com.example.base

import android.content.Intent

internal const val detailsIntent = "details.details"

private fun getIntent(filter: String): Intent {
    return Intent().apply { action = "com.example.moviesapp.$filter" }
}

fun getDetailIntent(): Intent {
    return getIntent(detailsIntent)
}