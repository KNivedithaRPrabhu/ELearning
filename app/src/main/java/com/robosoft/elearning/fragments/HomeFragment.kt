package com.robosoft.elearning.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.robosoft.elearning.R
import com.robosoft.elearning.adapter.RecyclerCurrentlyStudyingAdapter
import com.robosoft.elearning.api.CurrentlyStudyingDataClass

class HomeFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.currently_studying_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(container?.context)

       val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        recyclerView.layoutManager = layoutManager
        val adapter = RecyclerCurrentlyStudyingAdapter(currentlystudying())
        recyclerView.adapter = adapter
        return (view)


    }

    private fun currentlystudying() : List<CurrentlyStudyingDataClass>{
        return listOf(
            CurrentlyStudyingDataClass(
                R.drawable.img_biology,
                "Biology",
                "Introduction to Biology"
            ),
            CurrentlyStudyingDataClass(
                R.drawable.img_chemistry,
                "Chemistry",
                "Introduction to Chemistry"
            )
        )
    }


}