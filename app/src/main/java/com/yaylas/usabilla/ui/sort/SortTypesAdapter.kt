package com.yaylas.usabilla.ui.sort

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaylas.usabilla.databinding.ItemSortListBinding
import com.yaylas.usabilla.util.sort.SortType


class SortTypesAdapter(
    private val sortItemList: List<SortItem>,
    internal var selectedType: SortType
) : RecyclerView.Adapter<SortTypesAdapter.SortItemsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortItemsViewHolder {
        val binding = ItemSortListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SortItemsViewHolder(binding)
    }

    override fun getItemCount() = sortItemList.size

    override fun onBindViewHolder(holder: SortItemsViewHolder, position: Int) {
        with(holder) {
            with(sortItemList[position]) {
                binding.root.setOnCheckedChangeListener(null)
                binding.root.text = label
                binding.root.isChecked = sortType == selectedType
                binding.root.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) selectedType = sortType
                    notifyDataSetChanged()
                }
            }
        }
    }


    inner class SortItemsViewHolder(val binding: ItemSortListBinding) :
        RecyclerView.ViewHolder(binding.root)

}