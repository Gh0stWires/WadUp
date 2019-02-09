package tk.samgrogan.wadup.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.models.SearchWad

class SearchViewModel(val repo: NewWad): ViewModel() {

    fun searchWads(query: String): LiveData<SearchWad> {
        return repo.searchWads(query)
    }
}