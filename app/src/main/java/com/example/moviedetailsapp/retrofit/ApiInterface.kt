package com.example.moviedetailsapp.retrofit

import com.example.moviedetailsapp.data.moviedetailsdata.MovieDetails
import com.example.moviedetailsapp.data.moviedetailsdata.MovieResult


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
//https://www.omdbapi.com/?s=MammaMia&apikey=552191ee
//c55ee12c693ea15105a81afec129e1b1
    interface ApiInterface {
    @GET("?")
    suspend fun getMovie(
        @Query("s") s: String,
        @Query("apiKey") apiKey: String,
    ): Response<MovieResult>
    /*@GET("?")
    suspend fun getMovieDetails(
        @Query("t") t:String,
        @Query("y") y:String,
        @Query("apiKey") apiKey: String,
    ):Response<MovieDetails>*/
    @GET("?")
    suspend  fun getMovieDetails(
        @Query("i") t:String,
        @Query("apiKey") apiKey: String,
    ):Response<MovieDetails>
}


/*
https://www.omdbapi.com/?t=Harry+Potter&y=2011&apiKey=552191ee for movie details*/
