package com.example.moviedetailsapp.data.moviedetailsdata

data class MovieResult(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)