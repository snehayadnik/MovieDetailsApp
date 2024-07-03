package com.example.moviedetailsapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.moviedetailsapp.databinding.ActivityMovieDetailsBinding
import com.example.moviedetailsapp.retrofit.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private var imdbId: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imdbId=intent.getStringExtra("IMDB_ID")
        imdbId?.let { getMovieDetails(it) }

    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun getMovieDetails(imdbID:String) {
        GlobalScope.launch(Dispatchers.IO)
        {
            val response = try {
                RetrofitInstance.movieDetails.getMovieDetails(
                    imdbID,
                    "552191ee"
                )
            } catch (e: IOException) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
                return@launch
            } catch (e: HttpException) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        applicationContext,
                        "HTTP error ${e.message} ",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val newData = response.body()!!
                    binding.apply {
                        val poster = newData.Poster
                        Picasso.get().load(poster).resize(300, 400).into(movieposter)
                    }
                    binding.movieRatingSource.text = "Awards: ${newData.Awards}"
                    binding.movieRatingValue.text = "Languages: ${newData.Language}"
                    binding.movieGenre.text = "Genre: ${newData.Genre}"
                    binding.moviePlot.text = "Plot: ${newData.Plot}"
                    binding.movieRating.text = "Rating: ${newData.imdbRating}"
                }
                binding.backButton.setOnClickListener{
                    val intent= Intent(this@MovieDetailsActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                }
            }

        }

    }











