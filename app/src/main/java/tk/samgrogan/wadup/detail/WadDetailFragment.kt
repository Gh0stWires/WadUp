package tk.samgrogan.wadup.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_wad_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.R
import tk.samgrogan.wadup.api.models.WadDetail


class WadDetailFragment : Fragment() {
    val detailModel: WadDetailViewModel by viewModel()
    val detailItem: MutableList<DetailItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wad_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                this.title = "wad detail"
            }
        }
        observe()
    }

    fun observe() {
        detailModel.getWadDetails(arguments?.getString("id")).observe(this, Observer {
            detailSetup(it)
        })
    }

    fun detailSetup(wad: WadDetail) {
        wad.content.let {
            title.text = it.title
            detailItem.add(DetailItem("filename:", it.filename))
            detailItem.add(DetailItem("author:", it.author))
            detailItem.add(DetailItem("url:", it.idgamesurl))
            detailItem.add(DetailItem("size:", it.size.toString()))
            detailItem.add(DetailItem("credits:", it.credits.toString()))
            detailItem.add(DetailItem("base:", it.base))
            detailItem.add(DetailItem("buildtime:", it.buildtime))
            detailItem.add(DetailItem("editor(s):", it.editors))
            detailItem.add(DetailItem("bugs:", it.bugs))
            detailItem.add(DetailItem("description:", it.description))
        }
        val adapter = context?.let { DetailArrayAdapter(it, R.layout.detail_item, detailItem) }
        details.adapter = adapter

    }

}
