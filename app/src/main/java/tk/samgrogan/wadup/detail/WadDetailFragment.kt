package tk.samgrogan.wadup.detail


import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.amyu.stack_card_layout_manager.StackCardLayoutManager
import kotlinx.android.synthetic.main.fragment_wad_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import tk.samgrogan.wadup.api.models.WadDetail
import tk.samgrogan.wadup.common.addNotEmpty


class WadDetailFragment : Fragment() {
    val detailModel: WadDetailViewModel by viewModel()
    var detailItem: MutableList<DetailItem> = mutableListOf()
    lateinit var downloadUrl: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(tk.samgrogan.wadup.R.layout.fragment_wad_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).apply {
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                this.title = "wad detail"
            }
        }
        progress.visibility = View.VISIBLE
        download.setOnClickListener {
            startDownload()
        }
        //observe()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailItem = mutableListOf()
        observe()
    }


    fun observe() {
        detailModel.id = arguments?.getString("id").toString()
        detailModel.wadDetail.observe(viewLifecycleOwner, Observer {
            detailSetup(it)
            downloadUrl = createUrl(it.content.idgamesurl)
            progress.visibility = View.GONE
        })
    }

    fun detailSetup(wad: WadDetail) {
        wad.content.let {
            titleDetail.text = it.title
            detailItem.addNotEmpty(DetailItem("author", it.author))
            detailItem.addNotEmpty(DetailItem("credits", it.credits.toString()))
            detailItem.addNotEmpty(DetailItem("base", it.base))
            detailItem.addNotEmpty(DetailItem("buildtime", it.buildtime))
            detailItem.addNotEmpty(DetailItem("editor(s)", it.editors))
            detailItem.addNotEmpty(DetailItem("bugs", it.bugs))
            detailItem.addNotEmpty(DetailItem("description", it.description))

        }
        val adapter = context?.let { DetailRecyclerAdapter{
            (details.adapter as DetailRecyclerAdapter).clearData()
            NavHostFragment.findNavController(this).navigate(WadDetailFragmentDirections.actionWadDetailFragmentToSubDetailFragment(it.label,
                it.body.toString(), arguments?.getString("id").toString()
            ))
        } }
        adapter?.swapData(detailItem)
        val maxItemCount = 20
        details.layoutManager = StackCardLayoutManager(maxItemCount)
        details.adapter = adapter

    }

    fun createUrl(base: String): String {
        val halfUrl = base.substringAfter("//")
        return BASE_DOWNLOAD_URL + halfUrl
    }

    fun startDownload() {
        val mManager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val mRqRequest = DownloadManager.Request(
            Uri.parse(downloadUrl)
        )

        mRqRequest.setDescription("This is Test File")
        mRqRequest.setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        mRqRequest.setVisibleInDownloadsUi(true)
        mRqRequest.setMimeType("application/zip")
        //mRqRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "")
        val downloadId = mManager.enqueue(mRqRequest)
    }

    companion object {
        const val BASE_DOWNLOAD_URL = "http://ftp.mancubus.net/pub/idgames/"
    }

}
