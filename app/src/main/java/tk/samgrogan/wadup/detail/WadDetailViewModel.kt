package tk.samgrogan.wadup.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.models.WadDetail

class WadDetailViewModel(val repo: NewWad): ViewModel() {

    fun getWadDetails(id: String?): LiveData<WadDetail> {
        return repo.getWadDetails(id)
    }
}