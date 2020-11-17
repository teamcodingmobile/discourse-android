package io.keepcoding.discourse_android.UI.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import io.keepcoding.discourse_android.R

class SearchFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_fragment, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {


    }
}