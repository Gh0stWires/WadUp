package tk.samgrogan.wadup.home


import android.Manifest
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_new_wads.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.api.Status
import android.Manifest.permission_group.CONTACTS
import com.androidisland.ezpermission.EzPermission




class NewWadsFragment : Fragment() {
    private val wadModel: NewWadViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(tk.samgrogan.wadup.R.layout.fragment_new_wads, container, false)
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

        context?.let {
            EzPermission.with(it)
                .permissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request { granted, denied, permanentlyDenied ->
                    //Here you can check results...
                }
        }
        observe()
        refreshListener()
    }

    private fun observe() {
        wadModel.wadList.observe(this,
            Observer{

                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { it1 -> (wadRecycler.adapter as NewWadRecyclerAdapter).swapData(it1) }
                        progress.visibility = View.GONE
                        networkError.visibility = View.GONE
                        homeRefresh.isRefreshing = false
                    }
                    Status.ERROR -> {
                        networkError.visibility = View.VISIBLE
                        homeRefresh.isRefreshing = false
                        (wadRecycler.adapter as NewWadRecyclerAdapter).clearData()
                    }
                    Status.LOADING -> {
                        progress.visibility = View.VISIBLE
                        homeRefresh.isRefreshing = false
                    }
                }
            })
    }

    private fun refreshListener() {
        homeRefresh.setOnRefreshListener {
            wadModel.refreshData()
        }
    }

    companion object {
        const val REQUEST_CODE_SINGLE = 1022
    }
}
