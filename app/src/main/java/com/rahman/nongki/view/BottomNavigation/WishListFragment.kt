package com.rahman.nongki.view.BottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahman.nongki.databinding.FragmentWishListBinding
import com.rahman.nongki.model.ViewModelFactory
import com.rahman.nongki.model.dto.DataItem
import com.rahman.nongki.view.adapter.WishListAdapter


class WishListFragment : Fragment() {

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomNavViewModel: BottomNavViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavViewModel = obtainViewModel()

        bottomNavViewModel.getAllPlaces().observe(viewLifecycleOwner){
            if (it != null) {
                val listPlace = mutableListOf<DataItem>()
                var place: DataItem
                it.forEach{ nongki ->
                    place = DataItem(nongki.name, nongki.streetAddress, nongki.photoReference,
                    nongki.distance, nongki.regency, nongki.overallRating, nongki.latitude,
                    nongki.city, nongki.longitude, nongki.province, nongki.distanceTime,
                    nongki.userRatingTotal, nongki.district, nongki.placeId)
                    listPlace.add(place)
                }
                setListUsersData(listPlace)
            }
        }
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

    private fun setListUsersData(listPlace: List<DataItem>) {
        binding.rvWishList.layoutManager = LinearLayoutManager(requireContext())
        val listPlaceAdapter = WishListAdapter(listPlace)
        binding.rvWishList.adapter = listPlaceAdapter
    }

    private fun obtainViewModel(): BottomNavViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(this, factory).get(BottomNavViewModel::class.java)
    }
}