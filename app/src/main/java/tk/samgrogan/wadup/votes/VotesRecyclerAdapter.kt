package tk.samgrogan.wadup.votes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wad_item.view.*
import tk.samgrogan.wadup.R
import tk.samgrogan.wadup.api.models.WadVotes

class VotesRecyclerAdapter(val listener: (WadVotes.Content.Vote) -> Unit): RecyclerView.Adapter<VotesRecyclerAdapter.ViewHolder>() {

    var wads: List<WadVotes.Content.Vote> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotesRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.wad_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return wads.size
    }

    override fun onBindViewHolder(holder: VotesRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener(wads[position]) }
        holder.title.text = wads[position].title
        holder.author.text = wads[position].author
        holder.description.text = wads[position].description
        holder.rating.text = if (wads[position].rating.toString() == "null") "No Current Rating" else wads[position].rating.toString()

    }

    fun swapData(list: List<WadVotes.Content.Vote>) {
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