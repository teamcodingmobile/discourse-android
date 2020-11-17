package io.keepcoding.discourse_android

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.discourse_android.UI.login.SignInViewModel
import io.keepcoding.discourse_android.UI.login.SignUpViewModel
import io.keepcoding.discourse_android.UI.profile.ProfileFragmentViewModel
import io.keepcoding.discourse_android.UI.topics.TopicsFragmentViewModel
import io.keepcoding.discourse_android.UI.topics.create_topic.CreateTopicViewModel
import io.keepcoding.discourse_android.UI.topics.topic_detail.TopicDetailViewModel
import io.keepcoding.discourse_android.UI.topics.topic_detail.reply.ReplyTopicViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(private val application: Application) :
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(TopicsFragmentViewModel::class.java) -> TopicsFragmentViewModel(application)
                isAssignableFrom(TopicDetailViewModel::class.java) -> TopicDetailViewModel(application)
                isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(application)
                isAssignableFrom(SignInViewModel::class.java) -> SignInViewModel(application)
                isAssignableFrom(CreateTopicViewModel::class.java) -> CreateTopicViewModel(application)
                isAssignableFrom(ReplyTopicViewModel::class.java) -> ReplyTopicViewModel(application)
                isAssignableFrom(ProfileFragmentViewModel::class.java) -> ProfileFragmentViewModel(application)
                else -> throw IllegalArgumentException("Unknown ViewModel $modelClass")
            }
        } as T
    }
}

