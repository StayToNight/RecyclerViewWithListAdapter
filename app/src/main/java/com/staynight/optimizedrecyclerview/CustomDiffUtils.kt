package com.staynight.optimizedrecyclerview

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class CustomDiffUtils : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.description == newItem.description && oldItem.title == newItem.title
    }

    override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
        val diff = Bundle()
        if (oldItem.id == newItem.id) {
            return if (oldItem.description == newItem.description && oldItem.title == newItem.title)
                super.getChangePayload(oldItem, newItem)
            else {
                if (oldItem.title != newItem.title)
                    diff.putString(AdapterOptimized.ARG_TITLE, newItem.title)
                else
                    diff.putString(AdapterOptimized.ARG_DESCRIPTION, newItem.description)
                return diff
            }
        }
        return super.getChangePayload(oldItem, newItem)
    }
}