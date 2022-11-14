package com.robosoft.elearning.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.robosoft.elearning.databinding.FragmentSigninBinding
import java.util.regex.Pattern


class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSigninBinding.inflate(layoutInflater)
        binding.signFloatingBtn.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fragment: Fragment = VerifyAccountFragment()
                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(com.robosoft.elearning.R.id.fragmnet_container ,fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        })
        binding.dontHvAccSignupTv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fragment: Fragment = SignupFragment()
                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(com.robosoft.elearning.R.id.fragmnet_container ,fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        })
        return binding.root
    }

}

