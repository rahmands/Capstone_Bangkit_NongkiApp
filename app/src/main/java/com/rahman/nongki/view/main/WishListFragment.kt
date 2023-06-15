package com.rahman.nongki.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahman.nongki.data.local.Favorite
import com.rahman.nongki.databinding.FragmentWishListBinding
import com.rahman.nongki.model.ViewModelFactory
import com.rahman.nongki.view.adapter.WishListAdapter
import com.rahman.nongki.view.rekomendasi.MainActivity


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
        bottomNavViewModel = ViewModelProvider(requireActivity())[BottomNavViewModel::class.java]
        bottomNavViewModel.favoriteList.observe(requireActivity()){
            if (it != null){
                setListUsersData(it)
            }
        }
        return binding.root
    }

    private fun setListUsersData(listPlace: List<Favorite>) {
        binding.rvWishList.layoutManager = LinearLayoutManager(requireContext())
        val listPlaceAdapter = WishListAdapter(listPlace
        ) {
            startActivity(
                Intent(requireActivity(), MainActivity::class.java)
                    .putExtra(MainActivity.DATA, it)
            )
            //Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        }
        binding.rvWishList.adapter = listPlaceAdapter
    }

    private fun obtainViewModel(): BottomNavViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(this, factory).get(BottomNavViewModel::class.java)
    }
}