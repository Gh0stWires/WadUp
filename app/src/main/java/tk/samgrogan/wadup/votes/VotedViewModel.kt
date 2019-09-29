package tk.samgrogan.wadup.votes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.resources.VotedWadResource
import java.io.IOException

class VotedViewModel(val repo: NewWad): ViewModel() {

    private val reloadTrigger = MutableLiveData<Boolean>(true)
    val voted = Transformations.switchMap(reloadTrigger) {
        liveData {
            try {
                val listVoted = repo.getHighestVoted()
                emit(VotedWadResource.loading(listVoted.content.vote))
                emit(VotedWadResource.success(listVoted.content.vote))
            } catch (ioException: IOException) {
                emit(VotedWadResource.error(ioException.toString(), null))
            }
        }
    }

    fun refreshData() {
        reloadTrigger.value = true
    }

}