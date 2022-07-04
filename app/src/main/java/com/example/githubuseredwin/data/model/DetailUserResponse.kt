package com.example.githubuseredwin.data.model

data class DetailUserResponse(
    val id : Int,
    val following: Int,
    val followers: Int,
    val avatar_url : String,
    val login : String,
    val name: String,
    val followers_url: String,
    val following_url: String
)
