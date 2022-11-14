package com.robosoft.elearning.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.robosoft.elearning.R
import com.robosoft.elearning.adapter.ViewAdapter
import com.robosoft.elearning.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf(R.drawable.onboarding1 ,R.drawable.onboarding2 ,R.drawable.onboarding3)
        val title = listOf(
            getString(R.string.onboarding1_tv) ,getString(R.string.onboarding2_tv) ,getString(
                R.string.onboarding3_tv
            )
        )
        val desc = listOf(
            getString(R.string.onboarding_desc) ,getString(R.string.onboarding_desc) ,getString(
                R.string.onboarding_desc
            )
        )
        val adapter = ViewAdapter(images ,title ,desc)
        binding.viewPager2.adapter = adapter
        binding.getStartedTextview.visibility=View.INVISIBLE
        binding.dotsIndicator.setViewPager2(binding.viewPager2)
        var currentPage=0
        binding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position==2){
                    binding.getStartedTextview.visibility=View.VISIBLE
                    binding.skipTextview.visibility=View.INVISIBLE
                    currentPage=2
                }
                else{
                    binding.getStartedTextview.visibility=View.INVISIBLE
                    binding.skipTextview.visibility=View.VISIBLE
                    currentPage=1
                }
            }
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(
                position: Int ,
                positionOffset: Float ,
                positionOffsetPixels: Int
            ) {

            }
        })
        binding.nextPageBtn.setOnClickListener {
            if(currentPage<=3){
                Log.d("current_item",currentPage.toString())
                when(currentPage){
                    0->{
                        binding.viewPager2.currentItem=0
                        Log.d("current_item",currentPage.toString())
                        currentPage++
                    }
                    1->{
                        binding.viewPager2.currentItem=1
                        binding.viewPager2.setCurrentItem(1,true)
                        Log.d("current_item",currentPage.toString())
                        currentPage++
                    }
                    2->{
                        binding.viewPager2.currentItem=2
                        binding.viewPager2.setCurrentItem(2,true)
                        Log.d("current_item",currentPage.toString())
                        currentPage++
                    }
                    3->{
                        startActivity(Intent(applicationContext ,MainActivity::class.java))
                    }
                }
            }
        }
        binding.skipTextview.setOnClickListener {
            startActivity(Intent(applicationContext ,MainActivity::class.java))
        }
        binding.getStartedTextview.setOnClickListener {
            startActivity(Intent(applicationContext ,MainActivity::class.java))
        }

    }
}