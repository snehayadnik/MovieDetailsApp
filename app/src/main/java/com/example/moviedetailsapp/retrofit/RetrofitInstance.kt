package com.example.moviedetailsapp.retrofit

import com.example.moviedetailsapp.utils.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
         val movieDetails: ApiInterface by lazy{
             Retrofit.Builder()
                 .baseUrl(constants.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
                 .create(ApiInterface::class.java)
         }
}


