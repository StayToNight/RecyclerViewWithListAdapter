package com.staynight.optimizedrecyclerview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.staynight.optimizedrecyclerview.databinding.ActivityMainBinding
import com.staynight.optimizedrecyclerview.utils.BindingActivity
import com.staynight.optimizedrecyclerview.utils.PagingScrollListener

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val list1 = listOf(
        Item(1, "Product 1", "Description 1"),
        Item(2, "Product 2", "Description 2"),
        Item(3, "Product 3", "Description 3"),
        Item(4, "Product 4", "Description 4"),
        Item(5, "Product 5", "Description 5"),
        Item(6, "Product 6", "Description 6"),
        Item(7, "Product 7", "Description 7"),
        Item(8, "Product 8", "Description 8"),
        Item(9, "Product 9", "Description 9"),
        Item(10, "Product 10", "Description 10"),
        Item(11, "Product 11", "Description 11"),
        Item(12, "Product 12", "Description 12"),
        Item(13, "Product 13", "Description 13"),
        Item(14, "Product 10", "Description 10"),
        Item(15, "Product 11", "Description 11"),
        Item(16, "Product 12", "Description 12"),
        Item(17, "Product 13", "Description 13"),
        Item(18, "Product 10", "Description 10"),
        Item(19, "Product 11", "Description 11"),
        Item(20, "Product 12", "Description 12"),
        Item(21, "Product 13", "Description 13"),
        Item(22, "Product 10", "Description 10"),
        Item(23, "Product 11", "Description 11"),
        Item(24, "Product 12", "Description 12"),
        Item(25, "Product 13", "Description 13"),
        Item(26, "Product 10", "Description 10"),
        Item(27, "Product 11", "Description 11"),
        Item(28, "Product 12", "Description 12"),
        Item(29, "Product 13", "Description 13"),
    )

    private val list2 = listOf(
        Item(15, "Product 11", "Description 11"),
        Item(16, "Product 12", "Description 12"),
        Item(17, "Product 13", "Description 13"),
        Item(18, "Product 10", "Description 10"),
        Item(19, "Product 11", "Description 11"),
        Item(20, "Product 12", "Description 12"),
        Item(21, "Product 13", "Description 13"),
        Item(1, "Product 1", "Description 1"),
        Item(2, "Product 2", "Description 2"),
        Item(3, "Product 3", "Description 3"),
        Item(4, "Product 4", "Description 4"),
        Item(5, "Product 5", "Description 5"),
        Item(6, "Product 6", "Description 6"),
        Item(12, "Product 12", "Description 12"),
        Item(13, "Product 13", "Description 13"),
        Item(14, "Product 10", "Description 10"),
        Item(7, "Product 7", "Description 7"),
        Item(8, "Product 8", "Description 8"),
        Item(9, "Product 9", "Description 9"),
        Item(10, "Product 10", "Description 10"),
        Item(11, "Product 11", "Description 11"),
        Item(22, "Product 10", "Description 10"),
        Item(23, "Product 11", "Description 11"),
        Item(24, "Product 12", "Description 12"),
        Item(25, "Product 13", "Description 13"),
        Item(26, "Product 10", "Description 10"),
        Item(27, "Product 11", "Description 11"),
        Item(28, "Product 12", "Description 12"),
        Item(29, "Product 13", "Description 13"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        var items = 30
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = AdapterOptimized{
            if ((binding.rv.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0)
                binding.rv.scrollToPosition(0)
        }
        adapter.submitList(list1)
        binding.rv.adapter = adapter
        binding.rv.addOnScrollListener(object : PagingScrollListener() {
            override fun loadMoreItems() {
                val newItems = mutableListOf<Item>()
                for (i in items..(items + 10))
                    newItems.add(Item(i, "Product $i", "Description $i"))
                adapter.addItems(newItems)
                items += 10
            }
        })

        binding.btnAdd.setOnClickListener {
            adapter.insertItem(
                Item(0, "Product 0", "Description 0")
            )
        }

        binding.btnUpdate.setOnClickListener {
            adapter.submitList(list2)
        }

        binding.btnRemove.setOnClickListener {
            adapter.insertItem(
                Item(0, "Product 0", "Description 0")
            )
        }

        binding.btnRemove.setOnClickListener {
            adapter.removeItem(Item(7, "Product 7", "Description 7"))
        }

    }


}