package tk.samgrogan.wadup.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tk.samgrogan.wadup.api.models.Wad
import tk.samgrogan.wadup.api.models.WadDetail
import tk.samgrogan.wadup.api.models.WadVotes

interface NewWadService {
    @GET("api.php?action=latestfiles&limit=10&out=json")
    fun listRepos(): Call<Wad>

    @GET("api.php?action=get")
    fun getWadDetail(@Query("id") id: String?, @Query("out") out: String = "json"): Call<WadDetail>

    @GET("api.php?action=latestvotes&limit=10&out=json")
    fun getHighestVotes(): Call<WadVotes>
}

