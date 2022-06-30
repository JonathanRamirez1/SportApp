package com.cursosandroidant.sports

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import coil.transform.BlurTransformation
import com.cursosandroidant.sports.databinding.ItemSportBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/****
 * Project: Sports
 * From: com.cursosandroidant.sports
 * Created by Alain Nicolás Tello on 29/09/21 at 12:32
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
class SportAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<SportAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val sports = mutableListOf<Sport>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_sport, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sport = sports[position]

        with(holder) {
            setListener(sport)

            binding.tvName.text = sport.name

            val request = ImageRequest.Builder(context)
                .data(sport.imgUrl)
                .crossfade(true)
                .transformations(listOf(
                    BlurTransformation(context, 25f)
                ))
                .size(1280, 720)
                .target(
                    onStart = {
                        binding.imgPhoto.setImageResource(R.drawable.ic_access_time)
                    },
                    onSuccess = { resource ->
                        binding.progressBar.visibility = View.GONE
                        binding.imgPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.imgPhoto.setImageDrawable(resource)
                    },
                    onError = {
                        binding.progressBar.visibility = View.GONE
                        binding.imgPhoto.setImageResource(R.drawable.ic_error_outline)
                    }
                )
                .build()
            context.imageLoader.enqueue(request)
        }
    }

    override fun getItemCount(): Int = sports.size

     suspend fun add(sport: Sport) = withContext(Dispatchers.Main)  {
        if (!sports.contains(sport)) {
            sports.add(sport)
            notifyItemInserted(sports.size - 1)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSportBinding.bind(view)

        fun setListener(sport: Sport){
            binding.root.setOnClickListener { listener.onClick(sport) }
        }
    }
}