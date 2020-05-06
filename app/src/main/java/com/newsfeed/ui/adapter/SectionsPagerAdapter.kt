@file:Suppress("DEPRECATION")

package com.newsfeed.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.newsfeed.R
import com.newsfeed.ui.DisplayListFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4,
    R.string.tab_text_5,
    R.string.tab_text_6
)
class SectionsPagerAdapter(private val context: Context, private val codess : String, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment =  DisplayListFragment.newInstance()
        val bundle = Bundle()
        bundle.putString("sourcecode", codess)
        fragment.setArguments(bundle)
        context

        when (position) {
            0 -> {
                bundle.putString("content", "business")
            }
            1 -> {
                bundle.putString("content", "entertainment")
            }
            2 -> {
                bundle.putString("content", "health")
            }
            3 -> {
                bundle.putString("content", "science")
            }
            4 -> {
                bundle.putString("content", "sports")
            }
            5 -> {
                bundle.putString("content", "technology")
            }
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 6 total pages.
        return 6
    }
}