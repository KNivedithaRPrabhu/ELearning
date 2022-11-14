package com.robosoft.elearning.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.robosoft.elearning.R
import com.robosoft.elearning.api.CurrentlyStudyingDataClass

class RecyclerCurrentlyStudyingAdapter(private val currentlyStudyingList : List<CurrentlyStudyingDataClass>):
 RecyclerView.Adapter<RecyclerCurrentlyStudyingAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.current_studying_items,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentlyStudying = currentlyStudyingList[position]
        holder.image.setImageResource(currentlyStudying.img)
        holder.subject.text = currentlyStudying.subject
        holder.topic.text = currentlyStudying.topic
    }
    override fun getItemCount(): Int {
        return currentlyStudyingList.size
    }


    class ViewHolder(ItemView:View) : RecyclerView.ViewHolder(ItemView){
        val image:ImageView = itemView.findViewById(R.id.image_view)
        val subject : TextView = itemView.findViewById(R.id.subject_tv)
        val topic : TextView = itemView.findViewById(R.id.topic_tv)
    }

}