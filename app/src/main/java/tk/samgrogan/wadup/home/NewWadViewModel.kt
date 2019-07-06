package tk.samgrogan.wadup.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.resources.NewWadResource


class NewWadViewModel(val repo: NewWad) : ViewModel() {

    val wadList = liveData {

        try {
            val list = repo.getNewWads()
            emit(NewWadResource.loading(list.content.file))
            emit(NewWadResource.success(list.content.file))
        } catch (ioException: Exception) {
            emit(NewWadResource.error(ioException.toString(), null))
        }
    }
}