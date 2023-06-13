package com.rahman.nongki.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahman.nongki.data.local.Nongki
import com.rahman.nongki.databinding.ItemRecommendationBinding
import com.rahman.nongki.model.dto.DataItem

import com.rahman.nongki.view.rekomendasi.MainActivity

class RecommendationAdapter(private val recommendationList: List<DataItem>):
    RecyclerView.Adapter<RecommendationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun getItemCount(): Int = recommendationList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photoReference, namePlace, streetAddress, distance, distanceTime, overallRating, userRatingTotal) = recommendationList[position]
        Glide.with(holder.itemView.context)
            .load(photoReference)
            .into(holder.binding.ivRecommendation)
        holder.binding.placeRecommendation.text = namePlace.toString()
        holder.binding.addressRecommendation.text = streetAddress.toString()
        holder.binding.distanceRecommendation.text = distance.toString()
        holder.binding.estimasiRecommendation.text = distanceTime
        holder.binding.ratingRecommendation.text = overallRating.toString()
        holder.binding.totalRatingRecommendation.text = userRatingTotal.toString()

        holder.itemView.setOnClickListener{
            val intentMainActivity = Intent(holder.itemView.context, MainActivity::class.java)
            holder.itemView.context.startActivity(intentMainActivity)
        }
    }

    inner class ListViewHolder(val binding: ItemRecommendationBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(data: Nongki){
                binding.apply {
                    Glide.with(itemView.context).load(data.photoReference).into(ivRecommendation)
                    placeRecommendation.text = data.name
                    addressRecommendation.text = data.streetAddress
                    distanceRecommendation.text = data.distance.toString()
                    estimasiRecommendation.text = data.distanceTime.toString()
                    ratingRecommendation.text = data.overallRating.toString()
                    totalRatingRecommendation.text = data.userRatingTotal.toString()
                }
            }
        }
}