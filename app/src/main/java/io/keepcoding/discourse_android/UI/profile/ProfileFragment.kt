package io.keepcoding.discourse_android.UI.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.Utils
import kotlinx.android.synthetic.main.profile_fragment.*
import retrofit2.Response

class ProfileFragment() : Fragment() {

    private val mViewModel: ProfileFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(ProfileFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()

        mViewModel.getUser(85, object: DiscourseService.CallbackResponse<GetUserResponse> {
            override fun onResponse(response: GetUserResponse) {
                val user = mViewModel.parseUser(response)

                name.text = user.name
                username.text = user.username
                topicsCount.text = user.topicsCount.toString()
                postsCount.text = user.postsCount.toString()
                likesCount.text = user.likesCount.toString()

                val url = Utils().getURL(user.avatarTemplate, 100)

                Glide.with(requireActivity().application)
                    .load(url)
                    .circleCrop()
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.placeholder)
                    )
                    .into(avatar)
            }

            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                print(t.message)
            }
        })
    }
}