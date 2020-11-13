package io.keepcoding.discourse_android.UI.topics.topic_detail

import android.content.Context
import android.graphics.Typeface
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.Data.Models.AppModels.PostItem
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import kotlinx.android.synthetic.main.item_post.view.*
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist


class PostAdapter(private val context: Context, private val postList: List<PostItem>?): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    override fun getItemCount(): Int {
        return postList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        postList?.get(position).let { post ->
            holder.post = post
        }

    }

    inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_regular)
        var utils = Utils()
        var post: PostItem? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let {
                    itemView.labelDate.typeface = myCustomFontRegular
                    itemView.posterUsername.typeface = myCustomFontBold
                    itemView.labelContent.typeface = myCustomFontRegular
                    itemView.posterUsername.text = it.username.toString()
                    var content: String = it.content
                    var parsedContent = Jsoup.clean(content, Whitelist())
                    itemView.labelContent.text = parsedContent
                    var timeOffset = utils.getTimeOffset(it.date)
                    itemView.labelDate.text = utils.setTimeOffset(timeOffset, context)

                    Glide.with(context)
                            .load(it.URL)
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