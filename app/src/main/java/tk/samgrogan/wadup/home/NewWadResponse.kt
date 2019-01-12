package tk.samgrogan.wadup.home

import com.squareup.moshi.Json

data class NewWadResponse(
    val content: Content,
    val meta: Meta
)

data class Meta(
    val version: Int
)

data class Content(
    val `file`: List<File>
)

data class File(
    @field:Json(name = "author") val author: String,
    @field:Json(name = "description") val description: Any,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "rating") val rating: Any,
    @field:Json(name = "title") val title: String
)

