package tk.samgrogan.wadup.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import kotlinx.android.synthetic.main.detail_item.view.*

class DetailArrayAdapter(context: Context, @LayoutRes private val layoutRes: Int, private val list: MutableList<DetailItem>):
    BaseAdapter() {
    val context: Context = context

    override fun getView(position: Int, convetView: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val item = layoutInflater.inflate(layoutRes, viewGroup, false)

        item.label.text = list[position].label
        item.body.text = list[position].body
        return item
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}