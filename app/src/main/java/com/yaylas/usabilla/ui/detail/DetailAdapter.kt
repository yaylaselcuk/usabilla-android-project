package com.yaylas.usabilla.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaylas.usabilla.databinding.ItemDetailListBinding


class DetailAdapter(
    private val detailList: List<DetailInformationItem>
) : RecyclerView.Adapter<DetailAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = ItemDetailListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun getItemCount() = detailList.size

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        with(holder) {
            with(detailList[position]) {
                binding.tvTitle.text = title
                binding.tvValue.text = value
            }
        }
    }


    inner class DetailsViewHolder(val binding: ItemDetailListBinding) :
        RecyclerView.ViewHolder(binding.root)

}