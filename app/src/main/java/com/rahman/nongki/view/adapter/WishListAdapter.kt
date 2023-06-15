package com.rahman.nongki.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahman.nongki.data.local.Favorite
import com.rahman.nongki.databinding.ItemRowWishlistBinding

class WishListAdapter(private val listPlace: List<Favorite>, val onClick: (String) -> Unit) :
    RecyclerView.Adapter<WishListAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowWishlistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (placeID, images, name, streetAddress, overallRating) = listPlace[position]
        Glide.with(holder.itemView.context)
            .load(images)
            .into(holder.binding.ivWishList)
        holder.binding.tvSpotName.text = name
        holder.binding.tvStreetAddress.text = streetAddress.toString()

        holder.binding.root.setOnClickListener{
            onClick(listPlace[position]!!.placeID)
        }
    }

    override fun getItemCount(): Int = listPlace.size
}