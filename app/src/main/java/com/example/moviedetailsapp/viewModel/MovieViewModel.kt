/*
package com.example.moviedetailsapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedetailsapp.data.moviedetailsdata.MovieResult
import com.example.moviedetailsapp.data.moviedetailsdata.Search
import com.example.moviedetailsapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData= MutableLiveData<List<Search>>()
    fun getPopularMovies(){
    RetrofitInstance.movieDetails.getMovie("Mamma MiA",
        "552191ee").enqueue(object: Callback<MovieResult>{
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>){
                if(response.body()!=null){
                    movieLiveData.value=response.body()!!.Search
                }
                else{
                    return
                }
            }

        override fun onFailure(call: Call<MovieResult>, t: Throwable) {
            Log.d("TAG",t.message.toString())
        }
    })
    }
    fun observeMovieLiveData(): LiveData<List<Search>> {
        return movieLiveData
    }
}*/
