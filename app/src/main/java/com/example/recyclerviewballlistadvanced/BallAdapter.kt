package com.example.recyclerviewballlistadvanced

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewballlistadvanced.databinding.ItemLayoutBinding

class BallAdapter(
    val itemClicked: (Ball) -> Unit
) : ListAdapter<Ball, BallAdapter.ViewHolder>(BallDiffCallback()) {

    class BallDiffCallback : DiffUtil.ItemCallback<Ball>() {
        override fun areItemsTheSame(oldItem: Ball, newItem: Ball): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ball, newItem: Ball): Boolean {
            return oldItem == newItem
        }
    }

    // inner class for holding view items (viewholder)
    class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) { // apply view binding
        // assign the data content to the view holder
        fun bind(ball: Ball) {
//            binding.imageView.setImageResource(ball.imageId)
//            binding.textView.text = ball.name
            // apply data binding
            binding.ball = ball
            binding.executePendingBindings()
        }
    }

    // create a new viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(binding)

        return viewHolder
    }

    // get a viewholder and populate the data content
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ball = getItem(position)
        holder.bind(ball)
        // handle the item selection event
        holder.itemView.setOnClickListener { itemClicked(ball) }
    }
}


@BindingAdapter("ballImage")
//function name can be arbitrary
fun ImageView.setballImage(item: Ball?) {
    item?.let {
        setImageResource(item.imageId)
    }
}
