package io.keepcoding.discourse_android.UI.topics

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.Data.Models.TopicItem
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.item_topic.view.*
import java.util.*


class TopicsAdapter(private val context: Context, private val callbackTopicClick: CallbackTopicClick, private val topicList: List<TopicItem>?): RecyclerView.Adapter<TopicsAdapter.TopicHolder>() {


    override fun getItemCount(): Int {
        return topicList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        return TopicHolder(view)
    }

    override fun onBindViewHolder(holder: TopicHolder, position: Int) {
        topicList?.get(position).let { topic ->

            holder.itemView.setOnClickListener {
                callbackTopicClick.onItemClick(topic!!)
            }
            holder.topic = topic
        }


    }

    inner class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var topic: TopicItem? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let {
                    itemView.labelTitle.text = it.title
                    itemView.labelPosts.text = it.posts.toString()
                    itemView.labelViews.text = it.views.toString()
                    itemView.labelReplies.text = it.replies.toString()
                    itemView.linearLayout.setBackgroundColor(Color.parseColor("#F8F8F8"))
                    itemView.posterUsername.text = it.poster?.username.toString()
                    setTimeOffset(it.getTimeOffset())

                    Glide.with(context)
                            .load(it.poster?.URL)
                            .circleCrop()
                            .apply(
                                    RequestOptions()
                                            .placeholder(R.drawable.placeholder)
                            )
                            .into(itemView.posterImage)
                }
            }

        private fun setTimeOffset(timeOffset: TopicItem.TimeOffset) {

            val quantityString = when (timeOffset.unit) {
                Calendar.YEAR -> R.plurals.years
                Calendar.MONTH -> R.plurals.months
                Calendar.DAY_OF_MONTH -> R.plurals.days
                Calendar.HOUR -> R.plurals.hours
                else -> R.plurals.minutes
            }

            if (timeOffset.amount == 0) {
                itemView.labelDate.text =
                    itemView.context.resources.getString(R.string.minutes_zero)
            } else {
                itemView.labelDate.text =
                    itemView.context.resources.getQuantityString(
                        quantityString,
                        timeOffset.amount,
                        timeOffset.amount
                    )
            }
        }
    }

}