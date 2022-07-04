package com.example.githubuseredwin.ui.favoriteUser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubuseredwin.data.internal.UserDB
import com.example.githubuseredwin.data.internal.UserFavorite
import com.example.githubuseredwin.data.internal.UserFavoriteDao

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var userDAO: UserFavoriteDao?
    private var userDBs: UserDB?

    init {
        userDBs = UserDB.getDatabase(application)
        userDAO = userDBs?.userFavoriteDao()
    }

    fun getFavoriteUser(): LiveData<List<UserFavorite>>? {
        return userDAO?.getFavoriteUser()
    }

}