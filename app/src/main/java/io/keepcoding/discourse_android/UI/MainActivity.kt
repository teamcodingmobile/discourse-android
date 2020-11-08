package io.keepcoding.discourse_android.UI

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.profile.ProfileFragment
import io.keepcoding.discourse_android.UI.search.SearchFragment
import io.keepcoding.discourse_android.UI.topics.TopicsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TopicsFragment.TopicsInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_topics))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_search))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_profile))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = ViewPageAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        val myCustomFont : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_bold)
        toolbar_text.typeface = myCustomFont

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onCreateTopic() {
       //ir a crear topic
    }


}