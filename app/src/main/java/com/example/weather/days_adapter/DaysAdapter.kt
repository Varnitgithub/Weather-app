package com.example.weather.days_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model_classes.days_model

class DaysAdapter(private val days_list : ArrayList<days_model>, private val listener:click) : RecyclerView.Adapter<DaysAdapter.myviewholder>() {


    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day_01: TextView = itemView.findViewById(R.id.day_1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {

        val viewholder =  myviewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.days_layout, parent, false)
        )

        viewholder.day_01.setOnClickListener {
            listener.onclick(days_list[viewholder.adapterPosition],viewholder.adapterPosition)
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return days_list.size
    }
/*
    @SuppressLint("NotifyDataSetChanged")
    fun updatelist(newlist: ArrayList<hours_model>) {
        hours.clear()
        hours.addAll(newlist)
        notifyDataSetChanged()
    }*/

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val currentitem = days_list[position]
        holder.day_01.text= currentitem.day_01
        holder.day_01.setOnClickListener {
            if (currentitem.day_01 == "hour_01"){

            }
        }


    }

    interface click{
        fun onclick(daysModel: days_model,position: Int)
    }
}