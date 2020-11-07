package io.keepcoding.discourse_android.UI.topics

import io.keepcoding.discourse_android.Data.Models.TopicItem

interface CallbackTopicClick {
    fun onItemClick(topic: TopicItem)
}