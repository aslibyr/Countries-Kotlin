package com.aslibyr.countrieskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslibyr.countrieskotlin.model.Country

class CountryViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Türkiye","Asia","Ankara","TRY","Turkish","www.ss.com")
        countryLiveData.value = country
    }
}