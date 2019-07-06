package tk.samgrogan.wadup.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.models.WadDetail

class WadDetailViewModel(val repo: NewWad): ViewModel() {

    var detailList: MutableList<WadDetail> = mutableListOf()
    lateinit var id: String

    val wadDetail = liveData(Dispatchers.IO) {
        val list = repo.getWadDetails(id)
        emit(list)
    }
}