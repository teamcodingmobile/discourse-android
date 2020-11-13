package io.keepcoding.discourse_android.UI.topics

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import kotlinx.android.synthetic.main.item_topic.view.*


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
                callbackTopicClick.onItemClick(topic?.id!!)
            }
            holder.topic = topic
        }

    }

    inner class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_regular)
        var utils = Utils()
        var topic: TopicItem? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let {
                    itemView.labelTitle.typeface = myCustomFontRegular
                    itemView.labelPosts.typeface = myCustomFontRegular
                    itemView.labelViews.typeface = myCustomFontRegular
                    itemView.labelReplies.typeface = myCustomFontRegular
                    itemView.labelDate.typeface = myCustomFontRegular
                    itemView.posterUsername.typeface = myCustomFontBold
                    itemView.labelTitle.text = it.title
                    itemView.labelPosts.text = it.posts.toString()
                    itemView.labelViews.text = it.views.toString()
                    itemView.labelReplies.text = it.replies.toString()
                    itemView.linearLayout.setBackgroundColor(Color.parseColor("#F8F8F8"))
                    itemView.posterUsername.text = it.poster?.username.toString()
                    var timeOffset = utils.getTimeOffset(it.date)
                    itemView.labelDate.text = utils.setTimeOffset(timeOffset, context)

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

    }

}