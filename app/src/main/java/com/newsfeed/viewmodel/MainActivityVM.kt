package com.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import com.newsfeed.data.SourcesList

class MainActivityVM : ViewModel() {

     val sources = listOf(
        SourcesList("Australia", "au"),
        SourcesList("Brazil", "br"),
        SourcesList("China", "cn"),
        SourcesList("Egypt", "eg"),
        SourcesList("India", "in"),
        SourcesList("Japan", "jp"),
        SourcesList("Malaysia", "my"),
        SourcesList("Singapore", "sg"),
        SourcesList("Thailand", "th"),
        SourcesList("United States", "us")
    )

}