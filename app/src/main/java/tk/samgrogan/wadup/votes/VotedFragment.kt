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


class VotedFragment : Fragment() {

    val votedViewModel: VotedViewModel by viewModel()

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
            NavHostFragment.findNavController(this).navigate(VotedFragmentDirections.ActionVotedFragmentToWadDetailFragment(it.file.toString()))
        }

        observe()
    }

    private fun observe() {
        votedViewModel.getVotedList().observe(this, Observer {
            (votedRecycler.adapter as VotesRecyclerAdapter).swapData(it.content.vote)
            progress.visibility = View.GONE
        })
    }


}
