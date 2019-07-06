package tk.samgrogan.wadup.api

import retrofit2.http.GET
import retrofit2.http.Query
import tk.samgrogan.wadup.api.models.SearchWad
import tk.samgrogan.wadup.api.models.Wad
import tk.samgrogan.wadup.api.models.WadDetail
import tk.samgrogan.wadup.api.models.WadVotes

interface NewWadService {
    @GET("api.php?action=latestfiles&limit=10&out=json")
    suspend fun listRepos(): Wad

    @GET("api.php?action=get")
    suspend fun getWadDetail(@Query("id") id: String?, @Query("out") out: String = "json"): WadDetail

    @GET("api.php?action=latestvotes&limit=10&out=json")
    suspend fun getHighestVotes(): WadVotes

    @GET("api.php?action=search")
    suspend fun searchWads(@Query("query") query: String, @Query("type") type: String = "title", @Query("sort") sort: String = "rating", @Query("out") out: String = "json" ): SearchWad
}

