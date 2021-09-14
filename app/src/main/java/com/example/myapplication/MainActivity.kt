package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import org.joda.time.DateTime

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var fakeData: List<ItemDate> = mutableListOf()
    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker().apply {
            setTitleText("Select Date")
            setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        }.build()
    }

    private val adapter by lazy {
        DateAdapter(::itemRecyclerViewClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fakeData = listTest.toList()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        handlerClick()
    }

    private fun initView() {
        with(binding) {
            titleDateTime = DateTime.now().covertToMonthAndYear()
            rcvDate.adapter = adapter
            adapter.submitList(fakeData) {
                rcvDate.scrollToPosition(DateTime.now().dayOfMonth - 3)
                // GOI API HEAR
            }
        }
    }

    private fun handlerClick() {
        // Handler when user click button "oK" date picker
        datePicker.addOnPositiveButtonClickListener {
            val dayChoose = DateTime(it)
            // Replace new List
            binding.titleDateTime = dayChoose.covertToMonthAndYear()
            resetData(dayChoose)
        }
        // show Date picker
        binding.showDatePicker.setOnClickListener {
            datePicker.show(supportFragmentManager, null)
        }
    }

    // Handler when Item Click
    private fun itemRecyclerViewClick(itemDate: ItemDate) {
        resetData(itemDate.date)
        // Goi Api
    }

    private fun initListItemDate(listItemDate: List<ItemDate>, position: Int) {
        adapter.submitList(listItemDate) {
            scrollToPositionChoose(position)
        }
    }

    private fun scrollToPositionChoose(position: Int) {
        val pointCenterScreen = binding.rcvDate.width / 2 - binding.rcvDate[0].width / 2
        (binding.rcvDate.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            position,
            pointCenterScreen
        )
    }

    private fun resetData(value: DateTime) {
        // Day 1- 31  , list 0 ->30 => position = day - 1
        val itemDate = ItemDate(value, true)
        /**
         * CAll API HERE
         */

        val position = value.dayOfMonth - 1
        fakeData = value.getListDateInMonth()
        Log.e("aaa", itemDate.convertDateTimeToString())
        initListItemDate(listItemDate = fakeData, position = position)
    }

    companion object {
        var listTest = DateTime.now().getListDateInMonth()
    }
}