package tk.samgrogan.wadup.api.resources

import tk.samgrogan.wadup.api.Status
import tk.samgrogan.wadup.api.models.WadDetail

data class DetailWadResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun success(data: WadDetail.Content): DetailWadResource<WadDetail.Content> {
            return DetailWadResource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun error(msg: String, data: WadDetail.Content?): DetailWadResource<WadDetail.Content> {
            return DetailWadResource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun loading(data: WadDetail.Content): DetailWadResource<WadDetail.Content> {
            return DetailWadResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}