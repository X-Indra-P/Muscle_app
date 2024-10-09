package com.saxony.muscleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListMuscleAdapter(private val listMuscle : ArrayList<Muscle>) : RecyclerView.Adapter<ListMuscleAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Muscle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_muscle, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMuscle.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listMuscle[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMuscle[holder.adapterPosition]) }
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_description)

    }
}