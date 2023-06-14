package com.rahman.nongki.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahman.nongki.databinding.FragmentHomeBinding
import com.rahman.nongki.model.dto.DataItem
import com.rahman.nongki.view.adapter.RecommendationAdapter
import com.rahman.nongki.view.rekomendasi.MainActivity


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecommendationAdapter
    private lateinit var viewModel: BottomNavViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[BottomNavViewModel::class.java]
        viewModel.recommendationList.observe(requireActivity()){
            Log.e("masuk??", it!!.size.toString())
            setupRekomendasi(it as List<DataItem>)
        }
        return binding.root
    }

    private fun setupRekomendasi(listRecommendation: List<DataItem>) {
        binding.rvRecommended.layoutManager = LinearLayoutManager(requireActivity())
        val listRecommendationAdapter = RecommendationAdapter(listRecommendation,
        onClick = {
            startActivity(
                Intent(requireActivity(),MainActivity::class.java)
                    .putExtra(MainActivity.DATA,it)
            )
            //Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
        binding.rvRecommended.adapter = listRecommendationAdapter
    }

}