package com.mehnaz.gozayaanproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mehnaz.gozayaanproject.R
import com.mehnaz.gozayaanproject.data.models.CategoryItems

class HomeCategoryAdapter(private val items: List<CategoryItems>): RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryViewHolder>() {

    inner class HomeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.title)
        val image:ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return HomeCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
        holder.itemText.text = items[position].name
        holder.image.setImageResource(items[position].imageResId)
    }

    override fun getItemCount(): Int = 4
}