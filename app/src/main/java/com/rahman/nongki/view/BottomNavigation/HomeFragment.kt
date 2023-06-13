package com.rahman.nongki.view.BottomNavigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahman.nongki.SettingViewModel
import com.rahman.nongki.data.local.SettingDataStore
import com.rahman.nongki.data.local.SettingViewModelFactory
import com.rahman.nongki.data.local.dataStore
import com.rahman.nongki.databinding.FragmentHomeBinding
import com.rahman.nongki.model.ViewModelFactory
import com.rahman.nongki.model.dto.DataItem
import com.rahman.nongki.model.dto.DataItemLogin
import com.rahman.nongki.view.adapter.NearbyAdapter
import com.rahman.nongki.view.adapter.RecommendationAdapter

//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter1: RecommendationAdapter
    private lateinit var adapter2: NearbyAdapter
    private lateinit var viewModel: BottomNavViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //val view = inflater.inflate(R.layout.fragment_home, container, false)
        //recyclerView = view.findViewById(R.id.rv_recommended)
        //recyclerView = binding.rvRecommended

        //return view

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = obtainViewModel()

        recommendation()
        //nearby()

        //val preferences = SettingDataStore.getInstance(requireContext().dataStore)
        //val settingViewModel =
          //  ViewModelProvider(requireActivity(), SettingViewModelFactory(preferences))[SettingViewModel::class.java]

        //settingViewModel.saveUserId(userId = List<DataItemLogin?>()).observe(viewLifecycleOwner) {

        //}



    }

    private fun recommendation(){

        viewModel.getRecommendationData()
        viewModel.recommendationList.observe(viewLifecycleOwner){
            val listRecommendation = mutableListOf<DataItem>()
            setListRecommendationData(listRecommendation)
        }
    }

    //private fun nearby(){
    //    viewModel.getNearbyData()
    //    viewModel.nearbyList.observe(viewLifecycleOwner){
    //        val listNearby = mutableListOf<DataItem>()
    //        setListNearbyData(listNearby)
    //   }
    //}

    private fun obtainViewModel(): BottomNavViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(this, factory).get(BottomNavViewModel::class.java)
    }

    private fun setListRecommendationData(listRecommendation: List<DataItem>) {
        binding.rvRecommended.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val listPlaceAdapter = RecommendationAdapter(listRecommendation)
        binding.rvRecommended.adapter = listPlaceAdapter
    }

    //private fun setListNearbyData(listNearby: List<DataItem>) {
    //    binding.rvNearby.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    //    val listPlaceAdapter = NearbyAdapter(listNearby)
    //    binding.rvNearby.adapter = listPlaceAdapter
    //}
}