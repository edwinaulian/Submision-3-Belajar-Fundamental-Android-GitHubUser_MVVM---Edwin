package com.example.githubuseredwin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuseredwin.data.model.User
import com.example.githubuseredwin.databinding.SettingAppsBinding

class SettingAdapter : RecyclerView.Adapter<SettingAdapter.UserViewHolder>() {

    private val list = ArrayList<User>()

    inner class UserViewHolder(val binding: SettingAppsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    binding.switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    binding.switchTheme.isChecked = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = SettingAppsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }
}