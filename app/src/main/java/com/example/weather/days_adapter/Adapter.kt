package com.example.weather.days_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model_classes.hours_model

class adapter(val hours : ArrayList<hours_model>,private val listener: click) : RecyclerView.Adapter<adapter.myviewholder>() {


    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hour_1: TextView = itemView.findViewById(R.id.hour_1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {

        val viewholder =
            myviewholder( LayoutInflater.from(parent.context).inflate(R.layout.hours_layout, parent, false))
        viewholder.hour_1.setOnClickListener {
            listener.onclick(hours[viewholder.adapterPosition],viewholder.adapterPosition)
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return hours.size
    }
/*
    @SuppressLint("NotifyDataSetChanged")
    fun updatelist(newlist: ArrayList<hours_model>) {
        hours.clear()
        hours.addAll(newlist)
        notifyDataSetChanged()
    }*/

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val currentitem = hours[position]
        holder.hour_1.text = currentitem.hour_1


    }

    interface click{
        fun onclick(hoursModel: hours_model,position: Int)
    }
}