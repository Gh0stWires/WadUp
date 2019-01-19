package tk.samgrogan.wadup

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import tk.samgrogan.wadup.detail.WadDetailViewModel
import tk.samgrogan.wadup.home.NewWadViewModel

object AppModules {
    private val newWadViewModel = module {
        viewModel { NewWadViewModel(get())}
    }

    private val wadDetailViewModel = module {
        viewModel { WadDetailViewModel(get()) }
    }

    fun all() = DataModule.all() + listOf(newWadViewModel, wadDetailViewModel)
}