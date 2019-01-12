package tk.samgrogan.wadup.home

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface NewWadService {
    @GET("api.php?action=latestfiles&limit=10&out=json")
    fun listRepos(): Call<Wad>
}

class RetrofitRequest{
    val retrofit = Retrofit.Builder()
    .baseUrl("https://www.doomworld.com/idgames/api/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
}