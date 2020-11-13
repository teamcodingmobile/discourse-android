package io.keepcoding.discourse_android.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.topics.TopicsFragment
import io.keepcoding.discourse_android.UI.topics.topic_detail.TopicDetailFragment
import kotlinx.android.synthetic.main.activity_main.*

const val TRANSACTION_DETAIL_TOPIC = "detail_topic"

class MainActivity : AppCompatActivity(), TopicsFragment.TopicsInteractionListener, TopicDetailFragment.TopicDetailInteractionListener {


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

    override fun onItemClick(topicId: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.topicFragment, TopicDetailFragment(topicId))
                .addToBackStack(TRANSACTION_DETAIL_TOPIC)
                .commit()
    }

    override fun onBackButton() {
        supportFragmentManager.popBackStack()
    }


}