package tk.samgrogan.wadup

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import tk.samgrogan.wadup.home.NewWad
import tk.samgrogan.wadup.home.NewWadRepo
import tk.samgrogan.wadup.home.NewWadViewModel

object AppModules {
    private val newWadViewModel = module {
        viewModel { NewWadViewModel(get())}
    }

    fun all() = DataModule.all() + listOf(newWadViewModel)
}