package io.keepcoding.discourse_android.UI


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.login.FROM
import io.keepcoding.discourse_android.UI.login.SIGN_UP
import io.keepcoding.discourse_android.UI.topics.TopicsFragment
import io.keepcoding.discourse_android.UI.topics.create_topic.CREATE_TOPIC
import io.keepcoding.discourse_android.UI.topics.create_topic.CreateTopicActivity
import kotlinx.android.synthetic.main.tabs_activity.*

class TabsActivity : AppCompatActivity(), TopicsFragment.TopicsInteractionListener {

    var from: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabs_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_topics))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_search))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_profile))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        this.from = intent.getStringExtra(FROM) ?: ""

        init(from = from)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_TOPIC) {
            viewPager.currentItem = 0
            Snackbar.make(container, R.string.message_topic_created, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateTopic() {
        val intent = Intent(this, CreateTopicActivity::class.java)
        startActivityForResult(intent, CREATE_TOPIC)
    }

    fun init(from: String){
        if (from == SIGN_UP) {
            Snackbar.make(container, R.string.message_sign_up, Snackbar.LENGTH_LONG).show()
        }
    }


}