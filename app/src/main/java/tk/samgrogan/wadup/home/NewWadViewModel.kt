package tk.samgrogan.wadup.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.models.Wad


class NewWadViewModel(val repo: NewWad) : ViewModel() {

    fun getWadList(): LiveData<Wad> {
        return repo.getNewWads()
    }
}