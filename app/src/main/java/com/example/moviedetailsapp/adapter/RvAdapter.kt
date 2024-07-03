package com.example.moviedetailsapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetailsapp.data.moviedetailsdata.Search
import com.example.moviedetailsapp.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

/*class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    private var movieList = ArrayList<Search>()
    fun setMovieList(movieList: List<Search>) {
        this.movieList = movieList as ArrayList<Search>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemLayoutBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.binding.apply {
            val poster = currentItem.Poster
            Picasso.get().load(poster).resize(150, 200).into(movieIcon)
        }
        holder.binding.movieName.text = currentItem.Title
        holder.binding.movieYear.text = currentItem.Year
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}*/
class RvAdapter(private val searchArray: ArrayList<Search>) :
    RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val movieName = binding.movieName
        val movieYear = binding.movieYear

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = searchArray[position]
        holder.binding.apply {
            val poster = currentItem.Poster
            Picasso.get().load(poster).resize(150, 200).into(movieIcon)
        }
        holder.movieName.text = currentItem.Title
        holder.movieYear.text = currentItem.Year
        holder.itemView.setOnClickListener{
         if(onClickListener!=null){
             onClickListener!!.onClick(position,currentItem)
         }
        }
    }


    override fun getItemCount(): Int {
        return searchArray.size
    }

  fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

interface OnClickListener{
    fun onClick(position: Int,model:Search)
}
}




