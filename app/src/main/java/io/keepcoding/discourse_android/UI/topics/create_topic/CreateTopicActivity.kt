package io.keepcoding.discourse_android.UI.topics.create_topic


import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.Models.AppModels.PostModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostResponse
import io.keepcoding.discourse_android.R
import kotlinx.android.synthetic.main.create_topic_activity.*
import kotlinx.android.synthetic.main.create_topic_activity.viewLoading
import retrofit2.Response

const val CREATE_TOPIC = 100
const val CANCEL_TOPIC = 200

class CreateTopicActivity : AppCompatActivity(){

    private val mViewModel: CreateTopicViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(CreateTopicViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_topic_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()

        cancel_button.setOnClickListener(){
            setResult(CANCEL_TOPIC)
            finish()
        }

        create_button.setOnClickListener(){
            enableLoading()
            if (isFormValid()){
                val form = PostModel(
                        title = inputCreateTopic.text.toString(),
                        raw = inputCreateTopic.text.toString()
                )
                mViewModel.createTopic(object: DiscourseService.CallbackResponse<PostResponse>{
                    override fun onResponse(response: PostResponse) {
                        enableLoading(false)
                        setResult(CREATE_TOPIC)
                        finish()

                    }
                    override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                        enableLoading(false)
                        Snackbar.make(topic_create_container, "Error, Status code: $code", Snackbar.LENGTH_LONG).show()
                    }

                },  form = form)
            } else {
                enableLoading(false)
                showErrors()
            }
        }

    }


    fun init(){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_regular)
        toolbar_text.typeface = myCustomFontBold
        create_button.typeface = myCustomFontRegular
        cancel_button.typeface = myCustomFontRegular
        inputCreateTopic.typeface = myCustomFontRegular
    }

    private fun isFormValid(): Boolean {
        return inputCreateTopic.text.isNotEmpty()
                && inputCreateTopic.text.length >= 15
    }

    private fun showErrors() {
        if (inputCreateTopic.text.isEmpty()) {
            inputCreateTopic.error = getString(R.string.error_empty)
            return
        }

        if (inputCreateTopic.text.length < 15) {
            inputCreateTopic.error = getString(R.string.error_title_short)
            return
        }
    }

    private fun enableLoading(enabled: Boolean = true)  {
        if (enabled) {
            fragmentLoadingContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            fragmentLoadingContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }

    }

}