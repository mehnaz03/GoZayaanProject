package com.mehnaz.gozayaanproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mehnaz.gozayaanproject.R
import com.mehnaz.gozayaanproject.data.models.Property
import com.mehnaz.gozayaanproject.databinding.FragmentRecommendedListBinding
import com.mehnaz.gozayaanproject.ui.adapters.RecommendedAdapter

class RecommendedListFragment : Fragment() {

    private var _binding: FragmentRecommendedListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecommendedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recommendedList = arguments?.getSerializable("recommendedList") as? MutableList<Property>


        val recommendedAdapter = RecommendedAdapter(recommendedList ?: emptyList()) { property ->
            val bundle = Bundle().apply {
                putSerializable("property", property)
            }
            findNavController().navigate(R.id.action_recommended_to_details, bundle)
        }


        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        binding.recyclerView.adapter = recommendedAdapter
        binding.backButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
