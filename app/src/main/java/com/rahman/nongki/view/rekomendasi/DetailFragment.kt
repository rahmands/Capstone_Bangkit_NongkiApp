package com.rahman.nongki.view.rekomendasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rahman.nongki.R
import com.rahman.nongki.databinding.FragmentDetailBinding
import com.rahman.nongki.model.dto.OverviewItem
import com.rahman.nongki.model.dto.ReviewsItem
import com.rahman.nongki.view.adapter.ReviewAdapter


class DetailFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var userId: String
    private var isFavorite: Boolean =false
    private lateinit var favoritePlace: OverviewItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setDetail()
        mainViewModel.reviewList.observe(requireActivity()) {
            setReview(it as List<ReviewsItem>)
        }

        mainViewModel.favorite.observe(requireActivity()){
            isFavorite = if (it == null){
                binding.buttonFavorite.setImageResource(R.drawable.baseline_favorite_24)
                true
            } else {
                binding.buttonFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                false
            }
        }

        binding.buttonFavorite.setOnClickListener {view ->
            if (view.id == R.id.buttonFavorite){
                if (isFavorite){
                    mainViewModel.delFavorite(favoritePlace as OverviewItem)
                } else{
                    mainViewModel.addFavorite(favoritePlace as OverviewItem)
                }
            }
        }
        return binding.root
    }

    private fun setDetail(){
        mainViewModel.detailList.observe(viewLifecycleOwner){data ->
            binding.apply {
                namePlaceDetail.text = data!!.overview?.get(0)!!.name
                Glide.with(ivPlace).load(data!!.overview?.get(0)!!.images!![0]).into(ivPlace)
                addressPlaceDetail.text = data!!.overview?.get(0)!!.streetAddress
                regionPlaceDetail.text = data!!.overview?.get(0)!!.district
                cityPlaceDetail.text = data!!.overview?.get(0)!!.city
                tvAlamat.text = data!!.overview?.get(0)!!.formattedAddress
                tvPhone.text = data!!.overview?.get(0)!!.formattedPhone
                tvOpen.text = data!!.overview?.get(0)!!.open
                tvClose.text = data!!.overview?.get(0)!!.close
                tvListCategory.text = data!!.tags?.get(0)!!.categories.toString()
                tvListServiceOption.text = data!!.tags?.get(0)!!.categories.toString()
            }
        }
    }

    private fun setReview(listReview: List<ReviewsItem>){
        binding.rvReviews.layoutManager = LinearLayoutManager(requireActivity())
        val listReviewAdapter = ReviewAdapter(listReview)
        binding.rvReviews.adapter = listReviewAdapter
    }


}