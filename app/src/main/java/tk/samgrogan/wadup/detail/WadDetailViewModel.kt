package tk.samgrogan.wadup.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.models.WadDetail
import tk.samgrogan.wadup.api.resources.DetailWadResource
import java.io.IOException

class WadDetailViewModel(val repo: NewWad): ViewModel() {

    var detailList: MutableList<WadDetail> = mutableListOf()
    lateinit var id: String

    val wadDetail = liveData {
        try {
            val list = repo.getWadDetails(id)
            emit(DetailWadResource.loading(list.content))
            emit(DetailWadResource.success(list.content))
        } catch (ioException: IOException) {
            emit(DetailWadResource.error(ioException.toString(), null))
        }

    }
}