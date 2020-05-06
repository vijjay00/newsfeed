package com.newsfeed.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.newsfeed.R
import com.newsfeed.ui.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var sourcecode: String
    private lateinit var names: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val b = intent.extras
        if (b != null) {
            names = b.getString("title")!!
            sourcecode = b.getString("sourcecode")!!
        }
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = names
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(
            applicationContext,
            sourcecode,
            supportFragmentManager
        )
        view_pager.adapter = sectionsPagerAdapter
        view_pager.offscreenPageLimit = 6
        tabs.setupWithViewPager(view_pager)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
