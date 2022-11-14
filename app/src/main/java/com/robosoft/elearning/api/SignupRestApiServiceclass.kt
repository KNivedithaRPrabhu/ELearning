package com.robosoft.elearning.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.robosoft.elearning.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


    class SignupRestApiServiceclass {

        fun addUser(userData: SignupDataClass, onResult:(ResponseDataClass?) -> Unit){
            val retrofit = SignupServiceBuilderObject.buildService(SignupRestApi::class.java)

            retrofit.addUser(userData).enqueue(
                object : Callback<ResponseDataClass> {

                    override fun onResponse(
                        call: Call<ResponseDataClass> ,
                        response: Response<ResponseDataClass>
                    ) {
//                        Toast.makeText(this@SignupRestApiServiceclass,response.body()?.message,Toast.LENGTH_SHORT).
                        Log.d("msg",response.toString())
                    }

                    override fun onFailure(call: Call<ResponseDataClass> ,t: Throwable) {
                        onResult(null)
                    }
                })
        }
    }
