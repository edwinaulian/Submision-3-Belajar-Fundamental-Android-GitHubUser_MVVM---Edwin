package com.example.githubuseredwin.data.internal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserFavoriteDao {

    @Insert
    fun addToFavoriteTemp(favorite: UserFavorite)

    @Query("SELECT * FROM user_favorite")
    fun getFavoriteUser(): LiveData<List<UserFavorite>>

    @Query("SELECT count(*) FROM user_favorite WHERE user_favorite.id = :id")
    fun checkUser(id: Int): Int

    @Query("DELETE FROM user_favorite WHERE user_favorite.id = :id")
    fun deletedUserFromFavorite(id: Int): Int
}