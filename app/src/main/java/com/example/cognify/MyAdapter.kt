package com.example.cognify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class MyAdapter(private val tech_list : ArrayList<LearningTechs>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.technique_card,
        parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tech_list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tech_list[position]
        holder.tech_name.text = currentItem.tech_name
        holder.tech_detail.text = currentItem.tech_detail
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tech_name : TextView = itemView.findViewById(R.id.tech_name)
        val tech_detail : TextView = itemView.findViewById(R.id.tech_detail)


    }
}