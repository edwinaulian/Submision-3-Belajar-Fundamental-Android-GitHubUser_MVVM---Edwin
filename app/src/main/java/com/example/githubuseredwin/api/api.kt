package com.example.githubuseredwin.api

import com.example.githubuseredwin.data.model.DetailUserResponse
import com.example.githubuseredwin.data.model.User
import com.example.githubuseredwin.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("users")
    fun getListUsers(): Call<ArrayList<User>>

    @GET("search/users")
    fun getFindUser(@Query("q") query: String):Call<UserResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<User>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<User>>

}