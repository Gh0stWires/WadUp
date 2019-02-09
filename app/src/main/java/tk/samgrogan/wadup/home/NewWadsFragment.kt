package tk.samgrogan.wadup.home


import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_new_wads.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.api.models.Wad


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
            NavHostFragment.findNavController(this).navigate(NewWadsFragmentDirections.ActionNewWadsFragmentToWadDetailFragment(it.id.toString()))
        }
        observe()



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        listState = wadRecycler.layoutManager?.onSaveInstanceState()
        outState.putParcelable(LIST_STATE, listState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE)
        }
    }

    fun observe() {

        wadModel.getWadList().observe(this,
            androidx.lifecycle.Observer<Wad> {
                (wadRecycler.adapter as NewWadRecyclerAdapter).swapData(it.content.file)
            })


    }


    companion object {
        val LIST_STATE = "list-state"
    }
}
