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
import com.mehnaz.gozayaanproject.databinding.ItemRecommendedCardBinding

class RecommendedAdapter(private val propertyList: List<Property>, private val onItemClickListener: (Property) -> Unit) : RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {


    inner class RecommendedViewHolder(val binding: ItemRecommendedCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder {

        val binding = ItemRecommendedCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {

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
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.binding.cardImage)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = propertyList.size
}
