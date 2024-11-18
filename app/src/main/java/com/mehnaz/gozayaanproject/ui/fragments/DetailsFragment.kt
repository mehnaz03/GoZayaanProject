package com.mehnaz.gozayaanproject.ui.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.mehnaz.gozayaanproject.R
import com.mehnaz.gozayaanproject.data.models.Property
import com.mehnaz.gozayaanproject.databinding.FragmentDetailsBinding
import com.mehnaz.gozayaanproject.ui.adapter.BannerAdapter
import kotlinx.coroutines.*


class DetailsFragment : Fragment(),BannerAdapter.OnBackPressedListener {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bannerAdapter: BannerAdapter
    private var dots: Array<ImageView?> = arrayOf()

    private val totalPages get() = bannerAdapter.itemCount // Dynamically get the total pages
    private var autoSlideJob: Job? = null // Job for coroutine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val property = arguments?.getSerializable("property") as? Property
        property?.let {

            binding.tvTitle.text = it.property_name
            binding.tvLocation.text = it.location
            binding.tvDescription.text = it.description


            val fareText = "$ ${it.fare} "
            val fareUnitText = "/ ${it.fare_unit}".uppercase()

            val fullText = "$fareText$fareUnitText"
            val spannableString = SpannableString(fullText)


            val dollarSignEndIndex = "$".length
            spannableString.setSpan(
                AbsoluteSizeSpan(12, true),
                0, dollarSignEndIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )


            val startIndex = fareText.length
            val endIndex = fullText.length


            val color = Color.parseColor(
                    "#E6FFFFFF")
            spannableString.setSpan(
                ForegroundColorSpan(color),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )


            spannableString.setSpan(
                AbsoluteSizeSpan(10, true),  // Set the font size of the fare unit to 14px (or 14sp)
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            binding.tvPrice.text = spannableString


            binding.tvPrice.text = spannableString

            binding.ratingBar.text = it.rating.toString()

            val bannerImages = it.detail_images
            bannerAdapter = BannerAdapter(this,bannerImages,)
            binding.viewpagerBanner.adapter = bannerAdapter

            setupIndicatorDots(bannerImages.size)
            binding.viewpagerBanner.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    updateIndicator(position)
                }
            })

            startAutoSlide()
        }

        binding.btnBookNow.setOnClickListener {
            if (property?.is_available == true) {
                Toast.makeText(requireContext(), "Property is available!", Toast.LENGTH_SHORT).show()
            } else {

                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Property Unavailable")
            .setMessage("Sorry, this property is currently unavailable for booking.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

    }


    private fun startAutoSlide() {

        autoSlideJob?.cancel()


        autoSlideJob = CoroutineScope(Dispatchers.Main).launch {
            while (isActive) {
                delay(1500)


                binding.viewpagerBanner?.let {
                    if (it.currentItem + 1 >= totalPages) {
                        it.currentItem = 0
                    } else {
                        it.currentItem++
                    }
                }
            }
        }
    }

    private fun setupIndicatorDots(count: Int) {
        dots = arrayOfNulls(count)
        binding.paginationIndicator.removeAllViews()

        for (i in 0 until count) {
            dots[i] = ImageView(requireContext()).apply {
                setImageResource(R.drawable.indicator_inactive)
                val params = LinearLayout.LayoutParams(
                    16,
                    16
                )
                params.setMargins(8, 0, 8, 0)
                layoutParams = params
            }
            binding.paginationIndicator.addView(dots[i])
        }


        if (dots.isNotEmpty()) {
            dots[0]?.setImageResource(R.drawable.indicator_active)
        }
    }

    private fun updateIndicator(position: Int) {
        dots.forEachIndexed { index, imageView ->
            imageView?.setImageResource(
                if (index == position) R.drawable.indicator_active else R.drawable.indicator_inactive
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        autoSlideJob?.cancel()
    }

    override fun onBackPressed() {
        findNavController().navigateUp()
    }
}










