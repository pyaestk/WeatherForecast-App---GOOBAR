package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.TempDisplaySetting
import com.example.myapplication.TempDisplaySettingManager
import com.example.myapplication.utils.formatTempForDisplay
import com.example.myapplication.model.DailyForecast

class ForecastAdapter(
    private val tempDisplaySettingManager: TempDisplaySettingManager,
    private val clickHandler: (DailyForecast) -> Unit,

): ListAdapter<DailyForecast, ForecastAdapter.DailyForecastViewHolder>(
    DIFF_CONFIG
) {

    class DailyForecastViewHolder(view: View, private val tempDisplaySettingManager: TempDisplaySettingManager):RecyclerView.ViewHolder(view) {

        private val tempText: TextView = view.findViewById(R.id.tempText)
        private val desText: TextView = view.findViewById(R.id.desText)

        fun bind(dailyForecast: DailyForecast) {
            tempText.text = formatTempForDisplay(dailyForecast.temp, tempDisplaySettingManager.getTempDisplaySetting())
            desText.text = dailyForecast.description
        }

    }

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.forecastitem, parent, false)
        return DailyForecastViewHolder(itemView, tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }

}