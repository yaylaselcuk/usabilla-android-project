package com.yaylas.usabilla.util

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

class ActionLiveData : MutableLiveData<Unit>() {
    @MainThread
    fun call() {
        value = Unit
    }
}