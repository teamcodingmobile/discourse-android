package io.keepcoding.discourse_android.UI.search

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostsItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostsItemSearch
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import kotlinx.android.synthetic.main.item_post.view.*
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import java.util.*

class SearchAdapter (
        private val context: Context,
        private val postsSearchList: List<PostsItemSearch>?
):RecyclerView.Adapter<SearchAdapter.SearchHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return SearchHolder(view)

    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        postsSearchList?.get(position).let { post ->
            holder.post = post
        }

    }

    override fun getItemCount(): Int {
        return postsSearchList?.size ?: 0
    }

    inner class SearchHolder(v: View) : RecyclerView.ViewHolder(v){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_regular)
        var utils = Utils()
        var post: PostsItemSearch? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let {
                    itemView.labelDate.typeface = myCustomFontRegular
                    itemView.posterUsername.typeface = myCustomFontBold
                    itemView.labelContent.typeface = myCustomFontRegular
                    itemView.posterUsername.text = it.username.toString()
                    var blurb: String = it.blurb
                    var parsedBlurb = Jsoup.clean(blurb, Whitelist())
                    itemView.labelContent.text = it.blurb
                    var date: Date = utils.formatDate(it.date)
                    var timeOffset = utils.getTimeOffset(date)
                    itemView.labelDate.text = utils.setTimeOffset(timeOffset, context)

                    Glide.with(context)
                            .load(utils.getURL(it.avatarTemplate))
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