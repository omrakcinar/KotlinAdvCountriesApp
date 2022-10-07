package com.omerakcinar.kotlinadvcountriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omerakcinar.kotlinadvcountriesapp.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        //DUMMY DATA
        val country = Country("Turkey","Europe","Ankara","TRY","Turkish","www.ss.com")
        val country2 = Country("France","Europe","Paris","EUR","French","www.ss.com")
        val country3 = Country("Germany","Europe","Berlin","EUR","German","www.ss.com")
        val countryList = arrayListOf<Country>(country,country2,country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

}