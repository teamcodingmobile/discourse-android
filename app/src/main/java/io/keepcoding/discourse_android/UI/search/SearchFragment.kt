package io.keepcoding.discourse_android.UI.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostsItemSearch
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SearchResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.UsersItemSearch
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.search_fragment.*
import retrofit2.Response
import retrofit2.http.Query

class SearchFragment() : Fragment() {

    private var searchList: SearchResponse? = null
    private var postsSearchList: List<PostsItemSearch>? = null
    private var usersSearchList: List<UsersItemSearch>? = null

    private var mAdapterPosts: SearchPostsAdapter? = null
    private var mAdapterUsers: SearchUsersAdapter? = null
    private val mViewModel: SearchFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(SearchFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setViewsList()
    }

    private fun init() {
        postsRecyclerViewSearch.layoutManager = LinearLayoutManager(activity)
        postsRecyclerViewSearch.isNestedScrollingEnabled = false
        postsRecyclerViewSearch.setHasFixedSize(false)

        usersRecyclerViewSearch.layoutManager = LinearLayoutManager(activity)
        usersRecyclerViewSearch.isNestedScrollingEnabled = false
        usersRecyclerViewSearch.setHasFixedSize(false)

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getSearch(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

        segmentController.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setViewsList()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun getSearch(word: String){
        mViewModel.getSearch(word ,object : DiscourseService.CallbackResponse<SearchResponse> {

            override fun onResponse(response: SearchResponse) {
                searchList = response
                postsSearchList = searchList?.posts as List<PostsItemSearch>?
                usersSearchList = searchList?.users as List<UsersItemSearch>?

                if (postsSearchList?.size != 0) {
                    postsRecyclerViewSearch.visibility = View.VISIBLE
                    mAdapterPosts = SearchPostsAdapter(requireActivity(), postsSearchList)
                    postsRecyclerViewSearch.adapter = mAdapterPosts
                }else{
                    postsRecyclerViewSearch.visibility = View.INVISIBLE
                }

                if (usersSearchList?.size !=0){
                    usersRecyclerViewSearch.visibility = View.VISIBLE
                    mAdapterUsers = SearchUsersAdapter(requireActivity(), usersSearchList)
                    usersRecyclerViewSearch.adapter = mAdapterUsers
                }else {
                    postsRecyclerViewSearch.visibility = View.INVISIBLE

                }

            }

            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {

            }
        })
    }

    fun setViewsList(){
        if (segmentController.selectedTabPosition == 0){
            postsList.visibility = View.VISIBLE
            postsList.maxHeight = Int.MAX_VALUE
            usersList.visibility = View.VISIBLE

        } else if (segmentController.selectedTabPosition == 1){
            postsList.visibility = View.VISIBLE
            postsList.maxHeight = Int.MAX_VALUE
            usersList.visibility = View.INVISIBLE

        }else {
            postsList.visibility = View.INVISIBLE
            postsList.maxHeight = 0
            usersList.visibility = View.VISIBLE

        }
    }

}



