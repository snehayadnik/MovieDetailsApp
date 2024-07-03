package com.example.moviedetailsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetailsapp.R
import com.example.moviedetailsapp.adapter.RvAdapter
import com.example.moviedetailsapp.databinding.ActivityMainBinding
import com.example.moviedetailsapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviedetailsapp.data.moviedetailsdata.Search

import kotlinx.coroutines.DelicateCoroutinesApi


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newTextView: TextView

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getMovie()

        newRecyclerView = findViewById(R.id.rvMovie)
        newTextView = findViewById(R.id.textView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getMovie() {
        GlobalScope.launch(Dispatchers.IO)
        {
            val response = try {
                RetrofitInstance.movieDetails.getMovie(
                    "Harry Potter",
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
                    val data = response.body()!!
                    val searchArray: ArrayList<Search> = data.Search as ArrayList<Search>
                    val adapter = RvAdapter(searchArray)
                    newRecyclerView.adapter = adapter
                    adapter.setOnClickListener(object :
                        RvAdapter.OnClickListener {
                        override fun onClick(position: Int, model: Search) {

                            val intent =
                                Intent(this@MainActivity, MovieDetailsActivity::class.java)
                            intent.putExtra("IMDB_ID", model.imdbID)
                            startActivity(intent)
                        }
                    })
               }
                }

            }
        }


    }



