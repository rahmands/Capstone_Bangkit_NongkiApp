package com.rahman.nongki.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahman.nongki.databinding.ItemNearbyBinding
import com.rahman.nongki.databinding.ItemRecommendationBinding
import com.rahman.nongki.model.dto.DataItem

import com.rahman.nongki.view.rekomendasi.MainActivity

class NearbyAdapter (var nearbyList: List<DataItem>):
    RecyclerView.Adapter<NearbyAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemNearbyBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind =
            ItemNearbyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: NearbyAdapter.ListViewHolder, position: Int) {
        val (photoReference, name, streetAddress, distance, distanceTime, overallRating, userRatingTotal) = nearbyList[position]
        Glide.with(holder.itemView.context)
            .load(photoReference)
            .into(holder.binding.ivNearby)
        holder.binding.placeNearby.text = name.toString()
        holder.binding.addressNearby.text = streetAddress
        holder.binding.distanceNearby.text = distance.toString()
        holder.binding.estimasiNearby.text = distanceTime.toString()
        holder.binding.ratingNearby.text = overallRating.toString()
        holder.binding.totalRatingNearby.text = userRatingTotal.toString()

        holder.itemView.setOnClickListener{
            val intentMainActivity = Intent(holder.itemView.context, MainActivity::class.java)
            holder.itemView.context.startActivity(intentMainActivity)
        }
    }

    override fun getItemCount(): Int = nearbyList.size






    // inner class ListViewHolder(val binding: ItemRecommendationBinding) :
    //   RecyclerView.ViewHolder(binding.root){
    //     binding.apply
    //}
}