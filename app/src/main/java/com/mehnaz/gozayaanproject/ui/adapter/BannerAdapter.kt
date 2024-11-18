package com.mehnaz.gozayaanproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehnaz.gozayaanproject.R
import com.mehnaz.gozayaanproject.databinding.BannerItemBinding

class BannerAdapter(private val listener: OnBackPressedListener,private val imageList: List<String>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    interface OnBackPressedListener {
        fun onBackPressed()
    }
    inner class BannerViewHolder(val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val imageUrl = imageList[position]
        holder.binding.backButton.setOnClickListener {
            listener.onBackPressed() // Trigger the callback
        }


        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.binding.bannerImage)


    }

    override fun getItemCount(): Int = imageList.size
}
