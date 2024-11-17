package com.mehnaz.gozayaanproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehnaz.gozayaanproject.R
import com.mehnaz.gozayaanproject.data.models.CategoryItems
import com.mehnaz.gozayaanproject.data.models.Property
import com.mehnaz.gozayaanproject.databinding.RecommendedItemBinding

class HomeRecommendedAdapter(private val propertyList: List<Property>,private val onItemClickListener: (Property) -> Unit) : RecyclerView.Adapter<HomeRecommendedAdapter.HomeRecommendedViewHolder>() {


    inner class HomeRecommendedViewHolder(val binding: RecommendedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecommendedViewHolder {

        val binding = RecommendedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecommendedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecommendedViewHolder, position: Int) {

        val property = propertyList[position]
        holder.binding.root.setOnClickListener {
            // Trigger the click listener
            onItemClickListener(property)
        }
        holder.binding.property = property
        if (position == 0) {
            holder.binding.bookmarkIcon.visibility = VISIBLE

        } else {
            holder.binding.bookmarkIcon.visibility = GONE
        }
        Glide.with(holder.itemView.context)
            .load(property.hero_image)
            .into(holder.binding.cardImage)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = propertyList.size
}
