package com.robosoft.elearning.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.robosoft.elearning.R
import com.robosoft.elearning.api.SignupDataClass
import com.robosoft.elearning.api.SignupRestApiServiceclass
import com.robosoft.elearning.communicator.Communicator
import com.robosoft.elearning.databinding.ActivityMainBinding
import com.robosoft.elearning.databinding.FragmentSigninBinding
import com.robosoft.elearning.databinding.FragmentSignupBinding
import com.robosoft.elearning.fragments.SigninFragment
import com.robosoft.elearning.fragments.SignupFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(SigninFragment())
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmnet_container,fragment)
        fragmentTransaction.commit()
    }


}