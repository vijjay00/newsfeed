package com.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import com.newsfeed.data.Article
import com.newsfeed.data.DetailsList
import com.newsfeed.repository.DisplayRepository
import com.newsfeed.ui.DisplayListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayListViewModel : ViewModel() {

    fun getload(
        sourcecode: String,
        content: String,
        keys: String,
        displayAuthlistner: DisplayListFragment) {
        DisplayRepository().getDetails(sourcecode,content,keys).enqueue(object : Callback<DetailsList> {
            override fun onFailure(call: Call<DetailsList>, t: Throwable) {
                displayAuthlistner.onError(t.message.toString())
            }

            override fun onResponse(call: Call<DetailsList>, response: Response<DetailsList>) {
                if(response.isSuccessful){
                    response.body()?.articles?.let { displayAuthlistner.onSucess(it as ArrayList<Article>) }
                }else{
                    displayAuthlistner.onError(response.errorBody().toString())
                }
            }
        })
    }

    interface DisplayAuthlistner {
        fun onSucess(detailsList: ArrayList<Article>);
        fun onError(message : String);
    }
}
