package tk.samgrogan.wadup.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_new_wads.*
import org.koin.android.viewmodel.ext.android.viewModel


class NewWadsFragment : Fragment() {
    val wadModel: NewWadViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(tk.samgrogan.wadup.R.layout.fragment_new_wads, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //wadModel.getWadList().observe(this, Observer)
        observe()



    }

    fun observe() {

        wadModel.getWadList().observe(this,
            androidx.lifecycle.Observer<Wad> {
                wadRecycler.layoutManager = LinearLayoutManager(context)
                wadRecycler.adapter = NewWadRecyclerAdapter(it.content.file) {

                }
            })


    }


}
