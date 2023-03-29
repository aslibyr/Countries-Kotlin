package com.aslibyr.countrieskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aslibyr.countrieskotlin.R
import com.aslibyr.countrieskotlin.databinding.RowBinding
import com.aslibyr.countrieskotlin.model.Country
import com.aslibyr.countrieskotlin.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), ClickListener {

    class CountryViewHolder(var view: RowBinding ) : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RowBinding>(inflater,R.layout.row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
    override fun onCountryClicked(v: View) {
        val textView:TextView = v.findViewById(R.id.countryuuidText)
        val uuid = textView.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedToCountry(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}