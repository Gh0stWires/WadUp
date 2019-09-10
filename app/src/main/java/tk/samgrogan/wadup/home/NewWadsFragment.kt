package tk.samgrogan.wadup.home


import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_new_wads.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.api.Status


class NewWadsFragment : Fragment() {
    val wadModel: NewWadViewModel by viewModel()
    var listState: Parcelable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(tk.samgrogan.wadup.R.layout.fragment_new_wads, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (listState != null) {
            wadRecycler.layoutManager?.onRestoreInstanceState(listState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //wadModel.getWadList().observe(this, Observer)
        (activity as AppCompatActivity).apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                this.title = "Wad Up"
            }
        }
        wadRecycler.layoutManager = LinearLayoutManager(context)
        wadRecycler.adapter = NewWadRecyclerAdapter {
            //val action = NewWadsFragmentDirections.actionNewWadsFragmentToWadDetailFragment(it.id.toString())
            NavHostFragment.findNavController(this).navigate(NewWadsFragmentDirections.actionNewWadsFragmentToWadDetailFragment(it.id.toString()))
        }
        observe()
    }

    fun observe() {
        wadModel.wadList.observe(this,
            Observer{

                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { it1 -> (wadRecycler.adapter as NewWadRecyclerAdapter).swapData(it1) }
                        progress.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        networkError.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        progress.visibility = View.VISIBLE
                    }
                }
            })

    }
}
