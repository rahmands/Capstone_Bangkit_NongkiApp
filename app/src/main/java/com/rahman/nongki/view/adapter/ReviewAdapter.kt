package com.rahman.nongki.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahman.nongki.databinding.ItemRowReviewsBinding
import com.rahman.nongki.model.dto.ReviewsItem

class ReviewAdapter(private val review: List<ReviewsItem>):
RecyclerView.Adapter<ReviewAdapter.ListViewHolder>() {


    inner class ListViewHolder(var binding: ItemRowReviewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun getItemCount(): Int = review.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (star, name, reviewtext) = review[position]
        holder.binding.tvNamaReviewer.text = name
        holder.binding.tvRatingReview.text = star
        holder.binding.tvReview.text = reviewtext
    }
}