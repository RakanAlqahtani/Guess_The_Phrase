package com.example.guessthephrase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(val phrase : ArrayList<String>):RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val text = phrase[position]

        holder.itemView.apply {

            tvText.text = text
            if (text.startsWith("Found")){
                tvText.setTextColor(Color.GREEN)
            }else if (text.startsWith("No") || text.startsWith("Wrong")) {
                tvText.setTextColor(Color.RED)
            }else {

                tvText.setTextColor(Color.BLACK)

            }
        }
    }

    override fun getItemCount() = phrase.size
}