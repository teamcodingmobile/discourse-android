package io.keepcoding.discourse_android.UI.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserTopicsResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.topics.TopicsAdapter
import io.keepcoding.discourse_android.Utils
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.topics_fragment.*
import retrofit2.Response

class ProfileFragment() : Fragment() {
    private var mtopicsAdapter: TopicsAdapter? = null

    private val mViewModel: ProfileFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(ProfileFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    override fun onDetach() {
        super.onDetach()

        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private fun init() {


        topicList.layoutManager = LinearLayoutManager(activity)
        topicList.isNestedScrollingEnabled = false
    }

    override fun onResume() {
        super.onResume()
        val context = requireActivity().application
        val loginService = DiscourseService(context).loginService

        if (!loginService.isLogged()) {
            throw Exception("User is not logged in")
        }

        val loggedUserId = loginService.getUserId()
        val loggedUserUsername = loginService.getUsername()!!

        mViewModel.getUser(loggedUserId, object: DiscourseService.CallbackResponse<GetUserResponse> {
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

        mViewModel.getUserTopics(loggedUserUsername, object: DiscourseService.CallbackResponse<GetUserTopicsResponse> {
            override fun onResponse(response: GetUserTopicsResponse) {
                mtopicsAdapter = TopicsAdapter(
                    requireActivity().application,
                    null,
                    TopicItem.parseTopicsList(response)
                )
                topicList.adapter = mtopicsAdapter
            }

            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                println(t.message)
            }
        })
    }
}