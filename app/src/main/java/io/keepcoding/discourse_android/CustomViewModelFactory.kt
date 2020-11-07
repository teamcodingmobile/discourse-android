package io.keepcoding.discourse_android

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.discourse_android.UI.topics.TopicsFragmentViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(private val application: Application) :
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(TopicsFragmentViewModel::class.java) -> TopicsFragmentViewModel(application)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}