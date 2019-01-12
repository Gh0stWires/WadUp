package tk.samgrogan.wadup.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class NewWadViewModel(val repo: NewWad) : ViewModel() {

    fun getWadList(): LiveData<Wad> {
        return repo.getNewWads()
    }
}