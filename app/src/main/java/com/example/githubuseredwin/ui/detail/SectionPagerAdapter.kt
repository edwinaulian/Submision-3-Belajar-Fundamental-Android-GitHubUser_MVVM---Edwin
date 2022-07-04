package com.example.githubuseredwin.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubuseredwin.R

class SectionPagerAdapter (private val context: Context, fragement: FragmentManager, username: Bundle) : FragmentPagerAdapter(fragement, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var bundleFragmentData: Bundle

    init {
        bundleFragmentData = username
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_followers, R.string.tab_following)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.bundleFragmentData
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}