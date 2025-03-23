package com.example.systemserver.ui.app_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// View Model do AppListFragment
class AppListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aplicativos Instalados"
    }
    val text: LiveData<String> = _text

}