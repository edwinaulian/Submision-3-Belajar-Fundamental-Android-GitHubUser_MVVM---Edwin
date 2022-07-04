package com.example.githubuseredwin.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuseredwin.api.RetrofitClient
import com.example.githubuseredwin.data.internal.UserDB
import com.example.githubuseredwin.data.internal.UserFavorite
import com.example.githubuseredwin.data.internal.UserFavoriteDao
import com.example.githubuseredwin.data.model.DetailUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<DetailUserResponse>()
    private var userDAO: UserFavoriteDao?
    private var userDBs: UserDB?

    init {
        userDBs = UserDB.getDatabase(application)
        userDAO = userDBs?.userFavoriteDao()
    }

    fun setUserDetailAct(username: String) {
        RetrofitClient.apiInstance.getDetailUser(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d(FAILURE, "${t.message}")
                }

            })
    }

    fun getUserDetailAct(): LiveData<DetailUserResponse> {
        return user
    }

    fun addToFavorit(username: String, id: Int, avatarUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = UserFavorite(
                username, id, avatarUrl
            )
            userDAO?.addToFavoriteTemp(user)
        }
    }

    fun checkUser(id: Int) = userDAO?.checkUser(id)

    fun deletedUserFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO?.deletedUserFromFavorite(id)
        }
    }

    companion object {
        private const val FAILURE = "Failure"
    }

}