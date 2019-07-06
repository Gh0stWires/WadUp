package tk.samgrogan.wadup.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sub_detail.*
import tk.samgrogan.wadup.R

class SubDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subDetailTitle.text = arguments?.getString("detailTitle")
        subDetailBody.text = arguments?.getString("detailBody")
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            NavHostFragment.findNavController(this@SubDetailFragment).navigate(SubDetailFragmentDirections.actionSubDetailFragmentToWadDetailFragment(
                arguments?.getString("id").toString()
            ))
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }*/

}
