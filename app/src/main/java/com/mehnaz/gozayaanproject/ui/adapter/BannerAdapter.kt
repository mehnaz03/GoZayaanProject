package com.mehnaz.gozayaanproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehnaz.gozayaanproject.databinding.BannerItemBinding
import com.mehnaz.gozayaanproject.utils.BottomRoundedCornersTransformation

class BannerAdapter(private val imageList: List<String>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val imageUrl = imageList[position]



        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .transform(BottomRoundedCornersTransformation(30f)) // Apply bottom rounded corners with radius of 30px
            .into(holder.binding.bannerImage)


    }

    override fun getItemCount(): Int = imageList.size
}
