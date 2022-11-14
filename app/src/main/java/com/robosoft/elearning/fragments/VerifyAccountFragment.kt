package com.robosoft.elearning.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import com.robosoft.elearning.R
import com.robosoft.elearning.activity.Dummy
import com.robosoft.elearning.activity.HomePage
import com.robosoft.elearning.activity.MainActivity
import com.robosoft.elearning.databinding.FragmentVerifyAccountBinding

class VerifyAccountFragment : Fragment() {
    private var _binding: FragmentVerifyAccountBinding? = null
    private val binding get() = _binding!!

    companion object{
        private const val TEST_VERIFY_CODE = "1111"
    }

    private val fragmentFrameLayoutVerify: FrameLayout by lazy {
        binding.fragmentFrameLayoutVerify
    }

    private val otp1: EditText by lazy{
        binding.otp1
    }
    private val otp2: EditText by lazy{
        binding.otp2
    }
    private val otp3: EditText by lazy{
        binding.otp3
    }
    private val otp4: EditText by lazy{
        binding.otp4
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentVerifyAccountBinding.inflate(inflater, container, false)

        binding.closeButtonVerify.setOnClickListener {
            val intent = Intent(this@VerifyAccountFragment.requireContext(),MainActivity::class.java)
            startActivity(intent)
        }
        setListener()

        initFocus()
        return binding.root
    }
    private fun initFocus(){
        Log.d("initFocus","entered")
        otp1.isEnabled = true

        otp1.postDelayed({
            otp1.requestFocus()
            val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(otp1,InputMethodManager.SHOW_FORCED)
        },500)
    }

    private fun reset(){
        Log.d("reset","entered")
        otp1.isEnabled = false
        otp2.isEnabled = false
        otp3.isEnabled = false
        otp4.isEnabled = false

        otp1.setText("")
        otp2.setText("")
        otp3.setText("")
        otp4.setText("")

        otp1.setBackgroundResource(R.drawable.otp_box)
        otp2.setBackgroundResource(R.drawable.otp_box)
        otp3.setBackgroundResource(R.drawable.otp_box)
        otp4.setBackgroundResource(R.drawable.otp_box)

        initFocus()

    }

    private fun setListener(){
        Log.d("setListener","entered")
        fragmentFrameLayoutVerify.setOnClickListener {
            val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(fragmentFrameLayoutVerify.windowToken,0)
        }

        setTextChangeListener(fromEditText = otp1, targetEditText = otp2)
        setTextChangeListener(fromEditText = otp2, targetEditText = otp3)
        setTextChangeListener(fromEditText = otp3, targetEditText = otp4)
        setTextChangeListener(fromEditText = otp4, done={
            verifyOTPCode()
        })

        setKeyListener(fromEditText = otp2, backToEditText = otp1)
        setKeyListener(fromEditText = otp3, backToEditText = otp2)
        setKeyListener(fromEditText = otp4, backToEditText = otp3)
    }

    private fun setTextChangeListener(
        fromEditText: EditText ,
        targetEditText: EditText? = null ,
        done: (() -> Unit)? = null
    ) {
        Log.d("setTextChange","entered")
        fromEditText.addTextChangedListener {
            it?.let { string ->
                if(string.isNotEmpty()){

                    targetEditText?.let{editText ->
                        editText.isEnabled=true
                        editText.requestFocus()
                    }?:run{
                        done?.let { done ->
                            done()
                        }
                    }
                    fromEditText.clearFocus()
                    fromEditText.isEnabled=false

                }
            }
        }
    }
    private fun setKeyListener(fromEditText: EditText ,backToEditText: EditText){
        fromEditText.setOnKeyListener{_, _, event ->

            if(null != event && KeyEvent.KEYCODE_DEL==event.keyCode){
                Log.d("setKey","entered")
                backToEditText.isEnabled=true
                backToEditText.requestFocus()
                backToEditText.setText("")

                fromEditText.clearFocus()
                fromEditText.isEnabled=false
            }
            false
        }
    }
    private fun verifyOTPCode(){
        Log.d("verifyOTP","entered")
        val otpCode = otp1.text.toString().trim() +
                otp2.text.toString().trim() +
                otp3.text.toString().trim() +
                otp4.text.toString().trim()

        if(4!=otpCode.length){
            return
        }
        binding.nextBn.visibility=View.INVISIBLE
        binding.nextBnEnable.visibility= View.VISIBLE

        if(otpCode== TEST_VERIFY_CODE){
//            Toast.makeText(context,"msg",Toast.LENGTH_LONG).show()

            val inputManager= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(fragmentFrameLayoutVerify.windowToken,0)
            binding.nextBnEnable.setOnClickListener {
                val intent = Intent(this@VerifyAccountFragment.requireContext(),HomePage::class.java)
                startActivity(intent)
            }
            return
        }
        binding.nextBn.visibility=View.VISIBLE
        binding.nextBnEnable.visibility=View.INVISIBLE
        otp1.setBackgroundResource(R.drawable.otp_box_error)
        otp2.setBackgroundResource(R.drawable.otp_box_error)
        otp3.setBackgroundResource(R.drawable.otp_box_error)
        otp4.setBackgroundResource(R.drawable.otp_box_error)
        //Toast.makeText(context,"error",Toast.LENGTH_LONG).show()
//        reset()
        Handler().postDelayed({
            reset()
        }, 1000)

    }
}