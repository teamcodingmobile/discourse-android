package io.keepcoding.discourse_android.UI

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import io.keepcoding.discourse_android.UI.profile.ProfileFragment
import io.keepcoding.discourse_android.UI.search.SearchFragment
import io.keepcoding.discourse_android.UI.topics.TopicsFragment

class ViewPageAdapter(var context: Context, supportFragmentManager: FragmentManager, var totalTabs: Int
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TopicsFragment()
                
            }
            1 -> {
                SearchFragment()
            }
            2 -> {
                ProfileFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }


}