package io.keepcoding.discourse_android.UI.topics

import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem


interface CallbackTopicClick {
    fun onItemClick(topicId: String)
}