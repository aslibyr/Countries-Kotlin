package com.aslibyr.countrieskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslibyr.countrieskotlin.model.Country

class CountryViewModel(application: Application) : BaseViewModel(application) {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Türkiye", "Asia", "Ankara", "TRY", "Turkish", "www.ss.com")
        countryLiveData.value = country
    }
}