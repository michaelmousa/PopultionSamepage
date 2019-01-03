package com.treehouse.popultion

import android.support.constraint.R.id.parent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.FieldPosition

class PopAdapter: RecyclerView.Adapter<PopAdapter.PopViewHolder>() {

    private val data = arrayListOf<PopData>()

fun setData(data:List<PopData>) {

    this.data.clear()
    this.data.addAll(data)
    notifyDataSetChanged()
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopViewHolder {

        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PopViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: PopViewHolder, position: Int) {
        viewHolder.bind(data[position])

    }

    class PopViewHolder(private val rootView: View): RecyclerView.ViewHolder(rootView){

        fun bind(data: PopData){

            val textView =rootView.findViewById<TextView>(R.id.textView)
            textView.text= data.country.toString()
        }


    }
}