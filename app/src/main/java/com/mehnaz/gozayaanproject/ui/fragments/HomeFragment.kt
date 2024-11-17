package com.mehnaz.gozayaanproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mehnaz.gozayaanproject.R

import com.mehnaz.gozayaanproject.data.models.CategoryItems
import com.mehnaz.gozayaanproject.data.remote.ApiService
import com.mehnaz.gozayaanproject.data.remote.RetrofitInstance
import com.mehnaz.gozayaanproject.data.repository.Repository
import com.mehnaz.gozayaanproject.databinding.HomeFragmentBinding
import com.mehnaz.gozayaanproject.ui.adapters.HomeCategoryAdapter
import com.mehnaz.gozayaanproject.ui.adapters.HomeRecommendedAdapter
import com.mehnaz.gozayaanproject.ui.viewmodel.HomeViewModel
import com.mehnaz.gozayaanproject.ui.viewmodel.HomeViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val categoryItems = listOf(
            CategoryItems("Flights", R.drawable.flight),
            CategoryItems("Hotels", R.drawable.hotels),
            CategoryItems("Visa", R.drawable.visa),
            CategoryItems("Buses", R.drawable.bus)
        )


        val adapter = HomeCategoryAdapter(categoryItems)
        binding.recyclercategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclercategory.adapter = adapter


        val apiService = RetrofitInstance.retrofitInstance.create(ApiService::class.java)


        val propertyRepository = Repository(apiService)


        val homeViewModel = ViewModelProvider(this, HomeViewModelFactory(propertyRepository)).get(
            HomeViewModel::class.java)


        homeViewModel.properties.observe(viewLifecycleOwner, Observer {
            val recommendedadapter = HomeRecommendedAdapter(it) { property ->

                val bundle = Bundle().apply {
                    putSerializable("property", property)
                }
                findNavController().navigate(R.id.action_home_to_details, bundle)
            }
            binding.recyclerrecommended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerrecommended.adapter = recommendedadapter
        })


        homeViewModel.fetchProperties()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}
