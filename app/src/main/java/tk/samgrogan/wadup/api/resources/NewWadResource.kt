package tk.samgrogan.wadup.api.resources

import tk.samgrogan.wadup.api.Status
import tk.samgrogan.wadup.api.models.Wad

data class NewWadResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun success(data: List<Wad.Content.File>): NewWadResource<List<Wad.Content.File>> {
            return NewWadResource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun error(msg: String, data: List<Wad.Content.File>?): NewWadResource<List<Wad.Content.File>> {
            return NewWadResource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun loading(data: List<Wad.Content.File>): NewWadResource<List<Wad.Content.File>> {
            return NewWadResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}

