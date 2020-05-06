package com.newsfeed.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.newsfeed.R
import com.newsfeed.data.Article
import com.newsfeed.ui.NewsPageActivity
import com.newsfeed.utils.getDate
import com.newsfeed.utils.isNetworkAvailable
import com.newsfeed.utils.toast


class DisplayListAdapter (var sources: ArrayList<Article>, val mContext: Context) :
    RecyclerView.Adapter<DisplayListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_display, parent, false)
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val datas = sources.get(position)

        holder.titleText.text = datas.title
        if(datas.urlToImage!=null){
            Glide.with(mContext).load(datas.urlToImage).listener(object :
                RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    holder.progress_bar.setVisibility(View.GONE)
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean
                ): Boolean {
                    holder.progress_bar.setVisibility(View.GONE)
                    return false
                }
            }).into(holder.image)
        }
        else{
            holder.progress_bar.setVisibility(View.GONE)
            holder.noImage.setVisibility(View.VISIBLE)
        }

       holder.timeText.text = getDate(datas.publishedAt)
        holder.itemView.setOnClickListener {
            if(isNetworkAvailable(mContext)){
                mContext.run(fun Context.() {
                    startActivity(
                        Intent(mContext, NewsPageActivity::class.java).putExtra("name", datas.title).putExtra("url", datas.url)
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
        val titleText : TextView
            get() = itemView.findViewById(R.id.titleText)
        val timeText : TextView
            get() = itemView.findViewById(R.id.timeText)
        val image : AppCompatImageView
            get() = itemView.findViewById(R.id.image)
        val progress_bar : ProgressBar
            get() = itemView.findViewById(R.id.progress_bar)
        val noImage : TextView
            get() = itemView.findViewById(R.id.noImage)
    }

    init {
        this.sources = sources
    }
}