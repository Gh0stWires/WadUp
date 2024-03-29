package tk.samgrogan.wadup.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wad_item.view.*
import tk.samgrogan.wadup.R
import tk.samgrogan.wadup.api.models.Wad

class NewWadRecyclerAdapter(val listener: (Wad.Content.File) -> Unit): RecyclerView.Adapter<NewWadRecyclerAdapter.ViewHolder>() {

    var wads: List<Wad.Content.File> = listOf()
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener(wads[position]) }
        holder.title.text = wads[position].title
        holder.author.text = wads[position].author
        holder.description.text = wads[position].description
        holder.rating.text = if (wads[position].rating.toString() == "null") "No Current Rating" else wads[position].rating.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.wad_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return wads.size
    }

    fun swapData(list: List<Wad.Content.File>) {
        wads = list
        notifyDataSetChanged()
    }

    fun clearData() {
        wads = listOf()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val author = itemView.author
        val description = itemView.description
        val rating = itemView.rating
    }

}