package tk.samgrogan.wadup.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit






interface NewWadRepo {
    fun getNewWads(): LiveData<Wad>
}

class NewWad(private var retrofit: Retrofit) : NewWadRepo {
    private var api = retrofit.create(NewWadService::class.java)


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
