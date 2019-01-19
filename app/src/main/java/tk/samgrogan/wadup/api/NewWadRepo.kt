package tk.samgrogan.wadup.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import tk.samgrogan.wadup.api.models.Wad
import tk.samgrogan.wadup.api.models.WadDetail


interface NewWadRepo {
    fun getNewWads(): LiveData<Wad>
    fun getWadDetails(id: String?): LiveData<WadDetail>
}

class NewWad(private var retrofit: Retrofit) : NewWadRepo {
    private var api = retrofit.create(NewWadService::class.java)

    override fun getWadDetails(id: String?): LiveData<WadDetail> {
        val data = MutableLiveData<WadDetail>()

        api.getWadDetail(id).enqueue(object: Callback<WadDetail> {
            override fun onFailure(call: Call<WadDetail>, t: Throwable) {
                print("fail")
            }

            override fun onResponse(call: Call<WadDetail>, response: Response<WadDetail>) {
                data.value = response.body()
            }

        } )

        return data
    }

    override fun getNewWads(): LiveData<Wad> {
        var data = MutableLiveData<Wad>()

        api.listRepos().enqueue(object : Callback<Wad> {
            override fun onFailure(call: retrofit2.Call<Wad>, t: Throwable) {
                print("fail")
            }

            override fun onResponse(call: retrofit2.Call<Wad>, response: Response<Wad>) {
                //val moshi = Moshi.Builder().build()
                //val listMyData = Types.newParameterizedType(List::class.java, Wad::class.java)
                //val adapter: JsonAdapter<List<File>> = moshi.adapter(listMyData)
                data.value = response.body()
            }
        })

        return data
    }

}
