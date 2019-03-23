package tk.samgrogan.wadup.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.api.models.SearchWad
import tk.samgrogan.wadup.common.hide
import tk.samgrogan.wadup.common.hideKeyboard
import tk.samgrogan.wadup.common.show


class SearchFragment : Fragment() {

    val searchViewModel: SearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(tk.samgrogan.wadup.R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                this.title = "Wad Up"
            }
        }
        searchRecycler.layoutManager = LinearLayoutManager(context)
        searchRecycler.adapter = SearchRecyclerAdapter {
            NavHostFragment.findNavController(this).navigate(SearchFragmentDirections.ActionSearchFragmentToWadDetailFragment(it.id.toString()))
        }

        searchButton.setOnClickListener {
            if (queryBox.text != null) {
                search()
                motion.transitionToEnd()
            }
        }

        fabListener()

        motion.setTransitionListener(
            object: MotionLayout.TransitionListener {
                override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

                }

                override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                    if (currentId == tk.samgrogan.wadup.R.id.end) {
                        searchFAB.show()
                        searchContainer.hide(true)
                    }
                }
                // More code here
            }
        )


        searchRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                    searchFAB.hide()
                else if (dy < 0)
                    searchFAB.show()
            }
        })
    }

    private fun observe(query: String) {
        searchViewModel.repo.searchWads(query).observe(this, androidx.lifecycle.Observer<SearchWad> {
            (searchRecycler.adapter as SearchRecyclerAdapter).swapData(it.content.file)
        })
    }

    private fun search() {
        observe(queryBox.text.toString())
        hideKeyboard()
    }

    private fun fabListener() {
        searchFAB.setOnClickListener {
            fabCoord()
        }
    }

    private fun fabCoord() {
        searchFAB.hide()
        searchContainer.show()
        motion.transitionToStart()
        (searchRecycler.adapter as SearchRecyclerAdapter).clearData()
    }


}
