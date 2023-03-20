package com.example.cognify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val tech_list : ArrayList<LearningTechs>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mlistener : OnClickListener

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.technique_card,
        parent, false)

        return MyViewHolder(itemView, mlistener)
    }

    override fun getItemCount(): Int {
        return tech_list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tech_list[position]
        holder.tech_name.text = currentItem.tech_name
        holder.tech_detail.text = currentItem.tech_detail
    }

    fun setOnClickListener(listener: OnClickListener) {
        mlistener = listener
    }


    class MyViewHolder(itemView: View, listener: OnClickListener) : RecyclerView.ViewHolder(itemView) {

        val tech_name : TextView = itemView.findViewById(R.id.tech_name)
        val tech_detail : TextView = itemView.findViewById(R.id.tech_detail)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
}