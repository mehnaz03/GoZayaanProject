package com.mehnaz.gozayaanproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mehnaz.gozayaanproject.R
import com.mehnaz.gozayaanproject.data.models.Property
import com.mehnaz.gozayaanproject.databinding.FragmentDetailsBinding
import com.mehnaz.gozayaanproject.ui.adapter.BannerAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment() {

    // Declare the binding variable
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bannerAdapter: BannerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using ViewBinding
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val property = arguments?.getSerializable("property") as? Property


        // Check if property is not null and set data to the UI components
        property?.let {
            binding.tvTitle.text = it.property_name
            binding.tvLocation.text = it.location
            binding.tvDescription.text = it.description
            binding.tvPrice.text = "$ ${it.fare.toString()} /PER DAY"
            binding.ratingBar.text = it.rating.toString()

//            // Load image using Glide (if necessary)
//            Glide.with(requireContext())
//                .load(it.hero_image)
//                .into(binding.cardImage)
            bannerAdapter = BannerAdapter(it.detail_images)
            binding.viewpagerBanner.adapter = bannerAdapter

        }

        // Set up the "Book Now" button click listener
        binding.btnBookNow.setOnClickListener {
            // Handle the button click, e.g., navigate to another screen or show a dialog
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the binding to avoid memory leaks
        _binding = null
    }
}
