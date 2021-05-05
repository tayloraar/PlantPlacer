package com.tayloraar.plantplacer

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = mutableSelectedItem

    fun updateData(data: String) {
        mutableSelectedItem.value = data
    }
    fun selectItem(data: String) {
        mutableSelectedItem.value = data
    }
}