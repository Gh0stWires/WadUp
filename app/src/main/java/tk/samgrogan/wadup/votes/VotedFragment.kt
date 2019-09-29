package tk.samgrogan.wadup.votes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_voted.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.R
import tk.samgrogan.wadup.api.Status


class VotedFragment : Fragment() {

    private val votedViewModel: VotedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                this.title = "Wad Up"
            }
        }

        progress.visibility = View.VISIBLE
        votedRecycler.layoutManager = LinearLayoutManager(context)
        votedRecycler.adapter = VotesRecyclerAdapter {
            NavHostFragment.findNavController(this).navigate(VotedFragmentDirections.actionVotedFragmentToWadDetailFragment(it.file.toString()))
        }

        observe()
        refreshListener()
    }

    private fun observe() {
        votedViewModel.voted.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 -> (votedRecycler.adapter as VotesRecyclerAdapter).swapData(it1) }
                    progress.visibility = View.GONE
                    networkError.visibility = View.GONE
                    votedRefresh.isRefreshing = false
                }
                Status.ERROR -> {
                    networkError.visibility = View.VISIBLE
                    votedRefresh.isRefreshing = false
                    (votedRecycler.adapter as VotesRecyclerAdapter).clearData()
                }
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                    votedRefresh.isRefreshing = false
                }
            }
        })
    }

    private fun refreshListener() {
        votedRefresh.setOnRefreshListener {
            votedViewModel.refreshData()
        }
    }

}
