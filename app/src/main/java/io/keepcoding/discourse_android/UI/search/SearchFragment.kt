package io.keepcoding.discourse_android.UI.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostsItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostsItemSearch
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SearchResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.UsersItemSearch
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.search_fragment.*
import retrofit2.Response

class SearchFragment() : Fragment() {

    private var searchList: SearchResponse? = null
    private var postsSearchList: List<PostsItemSearch>? = null
    private var usersSearchList: List<UsersItemSearch>? = null

    private var mAdapter: SearchAdapter? = null
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
    }

    private fun init() {
        postsRecyclerViewSearch.layoutManager = LinearLayoutManager(activity)
        postsRecyclerViewSearch.isNestedScrollingEnabled = false
        postsRecyclerViewSearch.setHasFixedSize(false)

        search.setOnSearchClickListener(){
            getSearch("prueba")
        }

    }

    private fun getSearch(word: String){
        mViewModel.getSearch(word ,object : DiscourseService.CallbackResponse<SearchResponse> {

            override fun onResponse(response: SearchResponse) {
                searchList = response
                postsSearchList = searchList?.posts as List<PostsItemSearch>? ?: null
                usersSearchList = searchList?.users as List<UsersItemSearch>? ?: null

                mAdapter = SearchAdapter(requireActivity(), postsSearchList)
                postsRecyclerViewSearch.adapter = mAdapter

            }

            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {

            }
        })
    }
}

