package com.example.systemserver.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel para configurar a TextView na HomeFragment
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Utilize os botões para configurar o brilho da tela"
    }
    val text: LiveData<String> = _text
}