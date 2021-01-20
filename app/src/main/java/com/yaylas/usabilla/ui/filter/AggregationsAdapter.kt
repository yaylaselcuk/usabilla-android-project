package com.yaylas.usabilla.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.yaylas.usabilla.R

class AggregationsAdapter<T>(private val items: List<AggregationItem<T>>) : BaseAdapter(), SpinnerAdapter {
    override fun getCount() = items.size

    override fun getItem(position: Int): AggregationItem<T> = items[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item: AggregationItem<T> = items[position]
        val textView = if (convertView == null) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.spinner_item_textview, parent, false) as TextView
        } else {
            convertView as TextView
        }
        textView.text = item.label
        return textView
    }


    fun getItemPosition(o: T?): Int {
        if (o == null) {
            return 0
        }
        for (i in items.indices) {
            val item: AggregationItem<T> = items[i]
            if (item.associatedObject == o) {
                return i
            }
        }
        return 0
    }
}