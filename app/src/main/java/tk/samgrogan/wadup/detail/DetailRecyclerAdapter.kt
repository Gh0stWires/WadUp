package tk.samgrogan.wadup.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.detail_item_new.view.*
import tk.samgrogan.wadup.R

class DetailRecyclerAdapter(val listener: (DetailItem) -> Unit): RecyclerView.Adapter<DetailRecyclerAdapter.ViewHolder>() {
    private var list: List<DetailItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.detail_item_new, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener(list[position]) }
        holder.label.text = list[position].label
        holder.body.text = list[position].body
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun clearData() {
        list = listOf()
    }

    fun swapData(details: List<DetailItem>) {
        list = details
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val label = itemView.label
        val body = itemView.body
    }
}