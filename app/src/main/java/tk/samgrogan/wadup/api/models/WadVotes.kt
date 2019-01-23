package tk.samgrogan.wadup.api.models

import com.squareup.moshi.Json

data class WadVotes(
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
        @Json(name = "vote")
        val vote: List<Vote>
    ) {
        data class Vote(
            @Json(name = "author")
            val author: String,
            @Json(name = "description")
            val description: String,
            @Json(name = "file")
            val `file`: Int,
            @Json(name = "id")
            val id: Int,
            @Json(name = "rating")
            val rating: Double,
            @Json(name = "reviewtext")
            val reviewtext: Any?,
            @Json(name = "title")
            val title: String
        )
    }
}