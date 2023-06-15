package com.rahman.nongki.view.rekomendasi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rahman.nongki.R
import com.rahman.nongki.data.local.Favorite
import com.rahman.nongki.databinding.FragmentDetailBinding
import com.rahman.nongki.model.dto.ReviewsItem
import com.rahman.nongki.view.adapter.ReviewAdapter


class DetailFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    var placeId = String

    private var isFavorite: Boolean =false
    private var favoritePlace: Favorite? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setDetail()
        mainViewModel.reviewList.observe(requireActivity()) {
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
                tvClose.text = data!!.overview?.get(0)!!.close
                tvListCategory.text = data!!.tags?.get(0)!!.categories.toString()
                tvListServiceOption.text = data!!.tags?.get(0)!!.categories.toString()

                buttonMap.setOnClickListener {
                    navigateToMaps(
                        data!!.overview?.get(0)!!.latitude!!,
                        data!!.overview?.get(0)!!.longitude!!
                    )
                }

                mainViewModel.favorite.observe(requireActivity()){
                    if (it == true){
                        binding.buttonFavorite.setImageResource(R.drawable.baseline_favorite_24)
                        binding.buttonFavorite.setOnClickListener{
                            mainViewModel.delFavorite(
                                Favorite(
                                    data.overview?.get(0)!!.placeID,
                                    data.overview?.get(0)!!.images?.get(0),
                                    data.overview?.get(0)!!.name,
                                    data.overview?.get(0)!!.streetAddress,
                                    data.overview?.get(0)!!.overallRating
                                )
                            )
                        }
                    } else{
                        binding.buttonFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                        binding.buttonFavorite.setOnClickListener{
                            mainViewModel.addFavorite(
                                Favorite(
                                    data.overview?.get(0)!!.placeID,
                                    data.overview?.get(0)!!.images?.get(0),
                                    data.overview?.get(0)!!.name,
                                    data.overview?.get(0)!!.streetAddress,
                                    data.overview?.get(0)!!.overallRating
                                )
                            )
                        }
                    }
                }
            }
        }
    }



    private fun setReview(listReview: List<ReviewsItem>){
        binding.rvReviews.layoutManager = LinearLayoutManager(requireActivity())
        val listReviewAdapter = ReviewAdapter(listReview)
        binding.rvReviews.adapter = listReviewAdapter
    }

    fun navigateToMaps(latitude: Double, longitude: Double) {
        val uri = "geo:${latitude},${longitude}?q=${latitude},${longitude}(label)"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }


}