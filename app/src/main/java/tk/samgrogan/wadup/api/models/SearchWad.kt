package tk.samgrogan.wadup.api.models

import com.squareup.moshi.Json

data class SearchWad(
    @Json(name = "content")
    val content: Content,
    @Json(name = "meta")
    val meta: Meta
) {
    data class Content(
        @Json(name = "file")
        val `file`: List<File>
    ) {
        data class File(
            @Json(name = "age")
            val age: Int,
            @Json(name = "author")
            val author: String,
            @Json(name = "date")
            val date: String,
            @Json(name = "description")
            val description: String,
            @Json(name = "dir")
            val dir: String,
            @Json(name = "email")
            val email: String,
            @Json(name = "filename")
            val filename: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "idgamesurl")
            val idgamesurl: String,
            @Json(name = "rating")
            val rating: Double,
            @Json(name = "size")
            val size: Int,
            @Json(name = "title")
            val title: String,
            @Json(name = "url")
            val url: String,
            @Json(name = "votes")
            val votes: Int
        )
    }

    data class Meta(
        @Json(name = "version")
        val version: Int
    )
}