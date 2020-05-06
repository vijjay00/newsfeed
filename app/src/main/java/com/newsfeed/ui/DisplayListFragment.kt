@file:Suppress("DEPRECATION")

package com.newsfeed.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsfeed.R
import com.newsfeed.ui.adapter.DisplayListAdapter
import com.newsfeed.data.Article
import com.newsfeed.viewmodel.DisplayListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class DisplayListFragment : Fragment(), DisplayListViewModel.DisplayAuthlistner {

    companion object {
        fun newInstance() = DisplayListFragment()
    }

    private lateinit var sourcecode: String
    private lateinit var content: String
    private lateinit var myContext: Context
    private lateinit var linear : LinearLayoutManager
    private lateinit var viewModel: DisplayListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.myContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = this.arguments
        if(bundle!=null){
            sourcecode = bundle.getString("sourcecode").toString()
            content = bundle.getString("content").toString()
        }

        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DisplayListViewModel::class.java)
         viewModel.getload(sourcecode,content,resources.getString(R.string.news_apikey),this)
    }

    override fun onSucess(detailsList: ArrayList<Article>) {
        linear = LinearLayoutManager(myContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linear
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.left =  myContext.resources.getDimension(R.dimen.margin_7dp).toInt()
                outRect.right =  myContext.resources.getDimension(R.dimen.margin_7dp).toInt()
            }
        })
        recyclerView.adapter = DisplayListAdapter(detailsList,myContext)
        progress_bar.visibility = View.GONE
    }

    override fun onError(message: String) {

    }

}
