package com.yaylas.usabilla.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yaylas.usabilla.R
import com.yaylas.usabilla.databinding.ItemFeedbackListBinding
import com.yaylas.usabilla.domain.model.Feedback
import com.yaylas.usabilla.util.Util


class FeedbackAdapter(
    private val feedbackList: List<Feedback>,
    private val itemClicked: (item: Feedback) -> Unit
) : RecyclerView.Adapter<FeedbackAdapter.FeedbacksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbacksViewHolder {
        val binding = ItemFeedbackListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedbacksViewHolder(binding)
    }

    override fun getItemCount() = feedbackList.size

    override fun onBindViewHolder(holder: FeedbacksViewHolder, position: Int) {
        with(holder) {
            with(feedbackList[position]) {
                binding.tvDate.text = creationDate

                binding.tvComment.text = info.comment

                Glide.with(holder.itemView.context)
                    .load(images.list)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(binding.ivFeedback)

                binding.ratingBar.rating = info.rating.toFloat()

                binding.ivType.setImageResource(Util.getFeedbackTypeIconResId(info.feedbackType))

                if (info.labels.isNotEmpty()) {
                    binding.tvLabels.text = info.labels
                } else {
                    binding.tvLabels.text = ""
                }

                holder.itemView.setOnClickListener {
                    itemClicked(this)
                }
            }
        }
    }


    inner class FeedbacksViewHolder(val binding: ItemFeedbackListBinding) :
        RecyclerView.ViewHolder(binding.root)

}