package tk.samgrogan.wadup.api.models

import com.squareup.moshi.Json

data class Wad(
    @Json(name = "content")
    val content: Content,
    @Json(name = "meta")
    val meta: Meta
) {
    data class Meta(
        @Json(name = "version")
        val version: Int
    )

    data class Content(
        @Json(name = "file")
        val `file`: List<File>
    ) {
        data class File(
            @Json(name = "author")
            val author: String,
            @Json(name = "description")
            val description: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "rating")
            val rating: Any? = "No Current Rating",
            @Json(name = "title")
            val title: String
        )
    }
}