package tk.samgrogan.wadup.api.models

import com.squareup.moshi.Json

data class WadDetail(
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
        @Json(name = "age")
        val age: Int,
        @Json(name = "author")
        val author: String,
        @Json(name = "base")
        val base: String,
        @Json(name = "bugs")
        val bugs: String,
        @Json(name = "buildtime")
        val buildtime: String,
        @Json(name = "credits")
        val credits: Any?,
        @Json(name = "date")
        val date: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "dir")
        val dir: String,
        @Json(name = "editors")
        val editors: String,
        @Json(name = "email")
        val email: Any?,
        @Json(name = "filename")
        val filename: String,
        @Json(name = "id")
        val id: Int,
        @Json(name = "idgamesurl")
        val idgamesurl: String,
        @Json(name = "rating")
        val rating: Double,
        @Json(name = "reviews")
        val reviews: Reviews,
        @Json(name = "size")
        val size: Int,
        @Json(name = "textfile")
        val textfile: String,
        @Json(name = "title")
        val title: String,
        @Json(name = "url")
        val url: String,
        @Json(name = "votes")
        val votes: Int
    ) {
        data class Reviews(
            @Json(name = "review")
            val review: List<Review>
        ) {
            data class Review(
                @Json(name = "text")
                val text: String,
                @Json(name = "username")
                val username: String,
                @Json(name = "vote")
                val vote: Int
            )
        }
    }
}