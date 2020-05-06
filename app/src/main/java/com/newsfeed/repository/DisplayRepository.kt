package com.newsfeed.repository

import com.newsfeed.utils.MyApi
import com.newsfeed.data.DetailsList
import retrofit2.Call

class DisplayRepository {
    fun getDetails(sourcecode: String, content: String, keys: String) : Call<DetailsList> {
        return MyApi().fetchdetails(sourcecode,content,keys)
    }
}