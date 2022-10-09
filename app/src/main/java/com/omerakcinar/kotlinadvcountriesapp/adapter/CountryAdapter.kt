package com.omerakcinar.kotlinadvcountriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omerakcinar.kotlinadvcountriesapp.R
import com.omerakcinar.kotlinadvcountriesapp.databinding.RecyclerRowBinding
import com.omerakcinar.kotlinadvcountriesapp.model.Country
import com.omerakcinar.kotlinadvcountriesapp.util.downloadFromUrl
import com.omerakcinar.kotlinadvcountriesapp.util.placeholderProgressBar
import com.omerakcinar.kotlinadvcountriesapp.view.FeedFragmentDirections
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.recycler_row.view.*

class CountryAdapter (val countryList : ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(var view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.recycler_row,parent,false)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(inflater,R.layout.recycler_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this

        /*
        holder.view.countryNameRow.text = countryList[position].countryName
        holder.view.regionNameRow.text = countryList[position].countryRegion
        holder.view.flagViewRow.downloadFromUrl(countryList[position].imageUrl,
            placeholderProgressBar(holder.view.context))

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
         */

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}