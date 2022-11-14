package com.robosoft.elearning.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.elearning.R

class ViewAdapter(private val images:List<Int> ,private val title: List<String> ,private val desc:List<String>): RecyclerView.Adapter<ViewAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView =itemView.findViewById(R.id.image_view)
        val titleView: TextView =itemView.findViewById(R.id.title)
        val descView: TextView =itemView.findViewById(R.id.desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup ,viewType: Int): ViewPagerViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.items_view_pager ,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder ,position: Int) {
        val curImg=images[position]
        val curTitle=title[position]
        val curDesc=desc[position]
        holder.imgView.setImageResource(curImg)
        holder.titleView.text = curTitle
        holder.descView.text = curDesc
    }

    override fun getItemCount(): Int {
        return images.size
    }
}