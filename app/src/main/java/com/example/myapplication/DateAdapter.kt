package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ItemDatePickerBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseListAdapter

class DateAdapter(private val onItemClick: (ItemDate) -> Unit) :
    BaseListAdapter<ItemDate, ItemDatePickerBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return DateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_date_picker,
                parent,
                false
            ), onItemClick
        )
    }
}

class DateViewHolder(itemView: ItemDatePickerBinding, private val onItemClick: (ItemDate) -> Unit) :
    BaseViewHolder<ItemDate, ItemDatePickerBinding>(itemView) {
    override fun bind(item: ItemDate) {
        super.bind(item)
        binding.cardViewDate.setOnClickListener {
            onItemClick.invoke(item)
        }
    }
}