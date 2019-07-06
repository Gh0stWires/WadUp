package tk.samgrogan.wadup.api.resources

import tk.samgrogan.wadup.api.Status
import tk.samgrogan.wadup.api.models.WadVotes

data class VotedWadResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun success(data: List<WadVotes.Content.Vote>): VotedWadResource<List<WadVotes.Content.Vote>> {
            return VotedWadResource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun error(msg: String, data: List<WadVotes.Content.Vote>?): VotedWadResource<List<WadVotes.Content.Vote>> {
            return VotedWadResource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun loading(data: List<WadVotes.Content.Vote>): VotedWadResource<List<WadVotes.Content.Vote>> {
            return VotedWadResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}