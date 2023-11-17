package com.aslibyr.countrieskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aslibyr.countrieskotlin.R
import com.aslibyr.countrieskotlin.model.Country
import com.aslibyr.countrieskotlin.util.downloadFromUrl
import com.aslibyr.countrieskotlin.util.placeHolderProgressBar
import com.aslibyr.countrieskotlin.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.name)
        val textRegion: TextView = view.findViewById(R.id.region)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.textName.text = countryList[position].countryName
        holder.textRegion.text = countryList[position].countryRegion
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedToCountry(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.imageView.downloadFromUrl(countryList[position].imageUrl, placeHolderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}