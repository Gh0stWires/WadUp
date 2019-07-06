package tk.samgrogan.wadup.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tk.samgrogan.wadup.api.NewWad

class SearchViewModel(val repo: NewWad): ViewModel() {

    lateinit var query: String

    val searchWad = liveData(Dispatchers.IO) {
        val wads = repo.searchWads(query)
        emit(wads)
    }

}