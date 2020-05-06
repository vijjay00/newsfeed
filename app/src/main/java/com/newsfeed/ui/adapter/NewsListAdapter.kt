package com.newsfeed.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.newsfeed.R
import com.newsfeed.data.SourcesList
import com.newsfeed.ui.HomeActivity
import com.newsfeed.utils.isNetworkAvailable
import com.newsfeed.utils.toast

class NewsListAdapter(var sources: List<SourcesList>, val mContext: Context) :
    RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sources, parent, false)
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val datas = sources.get(position)

        holder.names.text = datas.name
        holder.itemView.setOnClickListener {
            if(isNetworkAvailable(mContext)){
                mContext.run(fun Context.() {
                    startActivity(Intent(mContext, HomeActivity::class.java).putExtra("title", datas.name)
                        .putExtra("sourcecode", datas.code).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                })
            }else{
                toast("No Internet Connection",mContext)
            }

        }
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class MyViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        val names : TextView
            get() = itemView.findViewById(R.id.name)
    }

    init {
        this.sources = sources
    }
}

