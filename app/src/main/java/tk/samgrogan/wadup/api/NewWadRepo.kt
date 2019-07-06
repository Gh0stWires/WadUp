package tk.samgrogan.wadup.api

import retrofit2.Retrofit


class NewWad(private var retrofit: Retrofit) {

    private var api = retrofit.create(NewWadService::class.java)

    suspend fun searchWads(query: String) = api.searchWads(query)

    suspend fun getHighestVoted() = api.getHighestVotes()

    suspend fun getWadDetails(id: String?) = api.getWadDetail(id)

    suspend fun getNewWads() = api.listRepos()

}
