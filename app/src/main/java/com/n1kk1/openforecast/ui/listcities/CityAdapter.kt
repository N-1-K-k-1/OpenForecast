package com.n1kk1.openforecast.ui.listcities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.n1kk1.openforecast.R
import com.n1kk1.openforecast.model.database.City
import kotlinx.android.synthetic.main.city_item.view.*
import kotlin.math.roundToInt

class CityAdapter(
    private val context: Context,
    private val onRemoveClicks: (City) -> Unit,
    private val onCityClicks: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var values: List<City> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(itemView)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(viewHolder: CityViewHolder, position: Int) {
        viewHolder.bind(values[position])
    }

    fun setItems(listCity: List<City>){
        values = listCity
        notifyDataSetChanged()
    }


    inner class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        init {
            itemView.v_close.setOnClickListener {
                onRemoveClicks.invoke(values[adapterPosition])
            }
            itemView.setOnClickListener {
                onCityClicks.invoke(values[adapterPosition])
            }
        }

        fun bind(item: City) {
            itemView.city_name.text = item.name
            itemView.temp.text = (context as AppCompatActivity).getString(R.string.temperature, item.temp.roundToInt(), "\u2103")
        }
    }
}