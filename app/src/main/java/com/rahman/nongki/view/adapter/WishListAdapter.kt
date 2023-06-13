package com.rahman.nongki.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahman.nongki.databinding.ItemRowWishlistBinding
import com.rahman.nongki.model.dto.DataItem

import com.rahman.nongki.view.rekomendasi.MainActivity

class WishListAdapter(private val listPlace: List<DataItem>) :
    RecyclerView.Adapter<WishListAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowWishlistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photoReference, name, streetAddress) = listPlace[position]
        Glide.with(holder.itemView.context)
            .load(photoReference)
            .into(holder.binding.ivWishList)
        holder.binding.tvSpotName.text = name
        holder.binding.tvStreetAddress.text = streetAddress

        holder.itemView.setOnClickListener {
            val intentDetailActivity = Intent(holder.itemView.context, MainActivity::class.java)
            //intentDetailActivity.putExtra("EXTRA_USERNAME", username)
            holder.itemView.context.startActivity(intentDetailActivity)
        }
    }

    override fun getItemCount(): Int = listPlace.size
}