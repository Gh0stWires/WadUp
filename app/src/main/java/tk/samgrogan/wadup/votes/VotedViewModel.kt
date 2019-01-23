package tk.samgrogan.wadup.votes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import tk.samgrogan.wadup.api.NewWad
import tk.samgrogan.wadup.api.models.WadVotes

class VotedViewModel(val repo: NewWad): ViewModel() {

    fun getVotedList(): LiveData<WadVotes> {
        return repo.getHighestVoted()
    }

}