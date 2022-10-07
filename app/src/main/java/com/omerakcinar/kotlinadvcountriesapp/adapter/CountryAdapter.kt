package com.omerakcinar.kotlinadvcountriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omerakcinar.kotlinadvcountriesapp.R
import com.omerakcinar.kotlinadvcountriesapp.databinding.RecyclerRowBinding
import com.omerakcinar.kotlinadvcountriesapp.model.Country
import com.omerakcinar.kotlinadvcountriesapp.view.FeedFragmentDirections

class CountryAdapter (val countryList : ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.countryNameRow.text = countryList[position].countryName
        holder.binding.regionNameRow.text = countryList[position].countryRegion

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}