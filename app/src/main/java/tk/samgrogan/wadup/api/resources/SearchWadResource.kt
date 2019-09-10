package tk.samgrogan.wadup.api.resources

import tk.samgrogan.wadup.api.Status
import tk.samgrogan.wadup.api.models.SearchWad

data class SearchWadResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun success(data: List<SearchWad.Content.File>): SearchWadResource<List<SearchWad.Content.File>> {
            return SearchWadResource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun error(msg: String, data: List<SearchWad.Content.File>?): SearchWadResource<List<SearchWad.Content.File>> {
            return SearchWadResource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun loading(data: List<SearchWad.Content.File>): SearchWadResource<List<SearchWad.Content.File>> {
            return SearchWadResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}