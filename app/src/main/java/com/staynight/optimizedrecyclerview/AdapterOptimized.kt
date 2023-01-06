package com.staynight.optimizedrecyclerview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.staynight.optimizedrecyclerview.databinding.ItemOptimizedBinding

class AdapterOptimized(
    private val scroll: () -> Unit
) :
    ListAdapter<Item, AdapterOptimized.OptimizedViewHolder>(CustomDiffUtils()) {

    companion object {
        const val ARG_TITLE = "ARG_TITLE"
        const val ARG_DESCRIPTION = "ARG_DESCRIPTION"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptimizedViewHolder =
        OptimizedViewHolder(
            ItemOptimizedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: OptimizedViewHolder, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun onBindViewHolder(
        holder: OptimizedViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.e("TAG", "onBindViewHolder: $position")
        val item = getItem(position)
        if (payloads.isEmpty() || payloads[0] !is Bundle)
            holder.bind(item)
        else if (payloads[0] is Bundle)
            holder.update(payloads[0] as Bundle)
    }

    fun insertItem(item: Item) {
        val newList = mutableListOf(item)
        currentList.forEach {
            if (it.id != item.id)
                newList.add(it)
        }
        submitList(newList) {
            scroll.invoke()
        }
    }

    fun removeItem(item: Item) {
        val newList = mutableListOf<Item>()
        for (i in currentList)
            if (i.id != item.id)
                newList.add(i)
        submitList(newList)
    }

    fun addItems(chats: List<Item>) {
        val newList = currentList + chats
        submitList(newList)
    }

    inner class OptimizedViewHolder(private val view: ItemOptimizedBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(item: Item) = with(view) {
            Log.e("TAG", "bind: $adapterPosition", )
            tvId.text = item.id.toString()
            tvDescription.text = item.description
            tvTitle.text = item.title
        }

        fun update(bundle: Bundle) = with(view) {
            Log.e("TAG", "update: $adapterPosition", )
            if (bundle.containsKey(ARG_TITLE))
                tvTitle.text = bundle.getString(ARG_TITLE)
            else if (bundle.containsKey(ARG_DESCRIPTION))
                tvDescription.text = bundle.getString(ARG_DESCRIPTION)
        }
    }
}