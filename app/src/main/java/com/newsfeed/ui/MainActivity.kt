package com.newsfeed.ui

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsfeed.R
import com.newsfeed.ui.adapter.NewsListAdapter
import com.newsfeed.viewmodel.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var viewModle : MainActivityVM
    private lateinit var griddy : GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModle = ViewModelProviders.of(this).get(MainActivityVM::class.java)
        setAdapter()
    }

    private fun setAdapter() {
        griddy = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = griddy
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.left =  applicationContext!!.resources.getDimension(R.dimen.margin_3dp).toInt()
                outRect.right =  applicationContext!!.resources.getDimension(R.dimen.margin_3dp).toInt()
            }
        })
        recyclerView.adapter = NewsListAdapter(viewModle.sources,applicationContext)
        progress_bar.visibility = View.GONE
    }

}
