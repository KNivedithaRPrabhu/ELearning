package com.robosoft.elearning.fragments

import android.R.attr.password
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.robosoft.elearning.api.SignupDataClass
import com.robosoft.elearning.api.SignupRestApiServiceclass
import com.robosoft.elearning.communicator.Communicator
import com.robosoft.elearning.databinding.FragmentSigninBinding
import com.robosoft.elearning.databinding.FragmentSignupBinding
import com.robosoft.elearning.databinding.FragmentVerifyAccountBinding
import kotlinx.android.synthetic.main.fragment_signup.*
import java.util.regex.Pattern


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var signinBinding: FragmentSigninBinding
//    private lateinit var communicator: Communicator
    val PASSWORD_PATTERN: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{8}\$"
//        "^" +
//                "(?=.*[@#$%^&+=])" +  // at least 1 special character
//                "(?=\\S+$)" +  // no white spaces
//                ".{4,}" +  // at least 4 characters
//                "$"
    )

    override fun onCreateView(
        inflater: LayoutInflater ,container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        binding.signupFloatingBtn.setOnClickListener {
            if (binding.nameEt.text.isNotEmpty() && binding.mobileEmailEt.text.isNotEmpty() && binding.createPasswordEt.text.isNotEmpty() && binding.confirmPasswordEt.text.isNotEmpty()) {
                if (binding.createPasswordEt.text.toString()
                        .equals(binding.confirmPasswordEt.text.toString())
                ) {
                        val apiService = SignupRestApiServiceclass()
                        val userInfo = SignupDataClass(
                            userName = binding.nameEt.text.toString() ,
                            email = binding.mobileEmailEt.text.toString() ,
                            userPassword = binding.confirmPasswordEt.text.toString()
                        )

                        apiService.addUser(userInfo) {
                            if (it  != null) {
                                Log.d("msg" ,"user added")
                            } else {
//                                Toast.makeText(context,"Registering new user",Toast.LENGTH_SHORT).show()
                                Log.d("msg" ,"Error registering new user")
                            }
                        }
                }
                else {
                    binding.confirmPasswordEt.error = "Password does not match"
                }
            } else {
                Toast.makeText(context ,"Please enter all the details" ,Toast.LENGTH_SHORT).show()
            }
//            isValidPassword(toString())

//            binding.signupFloatingBtn.setOnClickListener

        }

        signinBinding = FragmentSigninBinding.inflate(layoutInflater)

        binding.alreadyHvAccSigninTv.setOnClickListener (object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fragment: Fragment = SigninFragment()
                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(com.robosoft.elearning.R.id.fragmnet_container ,fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        })
        return binding.root

    }
    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
    }
}




