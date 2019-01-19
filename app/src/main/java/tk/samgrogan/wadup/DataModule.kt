package tk.samgrogan.wadup

import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tk.samgrogan.wadup.api.NewWad

object DataModule {

    private val retrofitModule = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://www.doomworld.com/idgames/api/")
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
        }
    }

    private val newWadRepModule = module {
        single {
            NewWad(get())
        }
    }

    fun all() = listOf(retrofitModule, newWadRepModule)
}