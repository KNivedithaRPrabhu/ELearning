package com.robosoft.elearning.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.robosoft.elearning.R
import com.robosoft.elearning.databinding.ActivityHomePageBinding
import com.robosoft.elearning.fragments.HomeFragment
import com.robosoft.elearning.fragments.ProfileFragment
import com.robosoft.elearning.fragments.SubjectFragment

class HomePage : AppCompatActivity() {
    private lateinit var binding:ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_nav->replaceFragment(HomeFragment())
                R.id.subject_nav->replaceFragment(SubjectFragment())
                R.id.profile_nav->replaceFragment(ProfileFragment())
                else->{

                }
            }
            true
        }
    }
    fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}