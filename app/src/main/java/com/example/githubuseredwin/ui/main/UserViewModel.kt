package com.example.githubuseredwin.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuseredwin.api.RetrofitClient
import com.example.githubuseredwin.data.model.User
import com.example.githubuseredwin.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {

    val listUser = MutableLiveData<ArrayList<User>>()

    fun getUsers() {
        val client = RetrofitClient.apiInstance.getListUsers()
        client.enqueue(object: Callback<ArrayList<User>>{
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d(FAILURE, "${t.message}")
            }
        })}

    fun setFindUser(query: String) {
        RetrofitClient.apiInstance.getFindUser(query).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body()?.items)
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(FAILURE, "${t.message}")
            }
        })
    }

    fun getFindUser(): LiveData<ArrayList<User>> {
        return listUser
    }

    companion object {
        private const val FAILURE = "Failure"
    }

}