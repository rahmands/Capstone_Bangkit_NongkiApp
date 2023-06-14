package com.rahman.nongki.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahman.nongki.databinding.ItemRecommendationBinding
import com.rahman.nongki.model.dto.DataItem

class RecommendationAdapter(private val recommendationList: List<DataItem?>?, val onClick: (String)-> Unit):
    RecyclerView.Adapter<RecommendationAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun getItemCount(): Int = recommendationList!!.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (placeId, name, latitude, longitude, overallRating,userRatingTotal, streetAddress, district, city, regency, province,
            distance, distanceTime,  photoReference) = recommendationList!![position]!!
        holder.binding.placeRecommendation.text = name
        holder.binding.ratingRecommendation.text = overallRating.toString()
        holder.binding.totalRatingRecommendation.text = userRatingTotal.toString()
        holder.binding.addressRecommendation.text = streetAddress.toString()
        holder.binding.distanceRecommendation.text = distance.toString()
        holder.binding.estimasiRecommendation.text = distanceTime.toString()
        Glide.with(holder.itemView.context)
            .load(photoReference)
            .into(holder.binding.ivRecommendation)


        holder.binding.root.setOnClickListener{
            onClick(recommendationList[position]!!.placeId)
        }
    }

    inner class ListViewHolder(val binding: ItemRecommendationBinding):
        RecyclerView.ViewHolder(binding.root)
}