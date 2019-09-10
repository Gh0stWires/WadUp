package tk.samgrogan.wadup.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.resources.SearchWadResource
import java.io.IOException

class SearchViewModel(val repo: NewWad): ViewModel() {

    lateinit var query: String

    val searchWad = liveData {
        try {
            val wads = repo.searchWads(query)
            emit(SearchWadResource.loading(wads.content.file))
            emit(SearchWadResource.success(wads.content.file))
        } catch (ioException: IOException) {
            emit(SearchWadResource.error(ioException.toString(), null))
        }

    }

}