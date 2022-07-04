package com.example.githubuseredwin.ui.favoriteUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuseredwin.data.internal.UserFavorite
import com.example.githubuseredwin.data.model.User
import com.example.githubuseredwin.databinding.ActivityFavoriteBinding
import com.example.githubuseredwin.ui.detail.DetailUserActivity
import com.example.githubuseredwin.ui.main.UserAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var adapter : UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUser.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if (it!=null) {
                val listData = mapList(it)
                adapter.setList(listData)
            }
        })
    }

    private fun mapList(users: List<UserFavorite>): ArrayList<User> {
        val listUser = ArrayList<User>()
        for (user in users) {
            val mapper = User(
                user.login, user.id, user.avatar_url
            )
            listUser.add(mapper)
        }
        return listUser
    }
}