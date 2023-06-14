package com.rahman.nongki.view.rekomendasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rahman.nongki.databinding.FragmentDetailBinding
import com.rahman.nongki.model.dto.ReviewsItem
import com.rahman.nongki.view.adapter.ReviewAdapter


class DetailFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setDetail()
        mainViewModel.detailList.observe(requireActivity()) {
            setReview(it as List<ReviewsItem>)

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