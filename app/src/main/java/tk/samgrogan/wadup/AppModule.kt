package tk.samgrogan.wadup

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import tk.samgrogan.wadup.detail.WadDetailViewModel
import tk.samgrogan.wadup.home.NewWadViewModel
import tk.samgrogan.wadup.search.SearchViewModel
import tk.samgrogan.wadup.votes.VotedViewModel

object AppModules {
    private val newWadViewModel = module {
        viewModel { NewWadViewModel(get())}
    }

    private val wadDetailViewModel = module {
        viewModel { WadDetailViewModel(get()) }
    }

    private val votedViewModel = module {
        viewModel { VotedViewModel(get()) }
    }

    private val searchViewModel = module {
        viewModel { SearchViewModel(get())}
    }

    fun all() = DataModule.all() + listOf(newWadViewModel, wadDetailViewModel, votedViewModel, searchViewModel)
}