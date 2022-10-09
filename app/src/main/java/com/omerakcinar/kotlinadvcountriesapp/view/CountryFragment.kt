package com.omerakcinar.kotlinadvcountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omerakcinar.kotlinadvcountriesapp.R
import com.omerakcinar.kotlinadvcountriesapp.databinding.FragmentCountryBinding
import com.omerakcinar.kotlinadvcountriesapp.util.downloadFromUrl
import com.omerakcinar.kotlinadvcountriesapp.util.placeholderProgressBar
import com.omerakcinar.kotlinadvcountriesapp.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {
    private var countryUuid = -1
    private lateinit var dataBinding: FragmentCountryBinding

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
            println(countryUuid)
        }

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { country  ->

                dataBinding.selectedCountry = country

                /*
                countryNameDetail.text = country.countryName
                countryCapitalDetail.text = country.countryCapital
                countryRegionDetail.text = country.countryRegion
                countryCurrencyDetail.text = country.countryCurrency
                countryLanguageDetail.text = country.countryLanguage
                context?.let {
                    flagImageDetail.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))
                }
                 */

            }
        })
    }

}