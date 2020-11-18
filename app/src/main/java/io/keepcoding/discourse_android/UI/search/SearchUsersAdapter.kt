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
import io.keepcoding.discourse_android.Data.Models.ResponseModels.UsersItemSearch
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import kotlinx.android.synthetic.main.item_users.view.*


class SearchUsersAdapter (
        private val context: Context,
        private val usersSearchList: List<UsersItemSearch>?
): RecyclerView.Adapter<SearchUsersAdapter.SearchHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return SearchHolder(view)

    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        usersSearchList?.get(position).let { user ->
            holder.user = user
        }

    }

    override fun getItemCount(): Int {
        return usersSearchList?.size ?: 0
    }

    inner class SearchHolder(v: View) : RecyclerView.ViewHolder(v){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(context, R.font.avenir_next_bold)
        var utils = Utils()
        var user: UsersItemSearch? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let {
                    itemView.posterUsernameUser.typeface = myCustomFontBold
                    itemView.posterUsernameUser.text = it.username


                    Glide.with(context)
                            .load(utils.getURL(it.avatarTemplate))
                            .circleCrop()
                            .apply(
                                    RequestOptions()
                                            .placeholder(R.drawable.placeholder)
                            )
                            .into(itemView.posterImageUser)
                }
            }
    }
}