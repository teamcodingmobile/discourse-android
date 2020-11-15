package io.keepcoding.discourse_android.UI.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.LoginService
import io.keepcoding.discourse_android.Data.Models.AppModels.SignInModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignInResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.TabsActivity
import io.keepcoding.discourse_android.UI.topics.topic_detail.PostAdapter
import io.keepcoding.discourse_android.UI.topics.topic_detail.TopicDetailViewModel
import kotlinx.android.synthetic.main.signin_activity.*
import kotlinx.android.synthetic.main.signin_activity.back_button
import kotlinx.android.synthetic.main.signin_activity.container
import kotlinx.android.synthetic.main.signin_activity.viewLoading
import kotlinx.android.synthetic.main.signin_fragment.*
import kotlinx.android.synthetic.main.signup_activity.*
import kotlinx.android.synthetic.main.topic_detail_activity.*
import retrofit2.Response

class SignInActivity : AppCompatActivity(),
        SignInFragment.SignInInteractionListener, RecoverFragment.RecoverInteractionListener{

    private val mViewModel: SignInViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(SignInViewModel::class.java)
    }

    val loginService = LoginService()

    val signInFragment = SignInFragment()
    val recoverFragment = RecoverFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, signInFragment)
                .commit()


        back_button.setOnClickListener(){
            finish()
        }


    }

    override fun onLogin(model: SignInModel) {
        enableLoading()
        mViewModel.login(object: DiscourseService.CallbackResponse<SignInResponse>{
            override fun onResponse(response: SignInResponse) {
                enableLoading(false)
                handleResponse(username = inputSignInUsername.text.toString())
            }

            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                enableLoading(false)
                handleError(code)
            }

        }, username = model.username)
    }

    override fun onRecoverPassword() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, recoverFragment)
                .commit()
    }

    override fun onBackToLogin() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, signInFragment)
                .commit()
    }

    override fun onSendEmail() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //llamar a la api y volver a sign in (mostrando mensaje)
    }

    private fun enableLoading(enabled: Boolean = true)  {
        if (enabled) {
            container.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            container.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
    }

    private fun handleResponse(username: String){
            loginService.saveSession(this, username)
            val intent = Intent(this, TabsActivity::class.java)
            startActivity(intent)
    }

    private fun handleError(code:Int){
        if (code == 404){
            Snackbar.make(signInContainer, getString(R.string.user_not_found), Snackbar.LENGTH_LONG).show()
            return
        }
        if (code == 403){
            Snackbar.make(signInContainer, getString(R.string.error_unauthorized), Snackbar.LENGTH_LONG).show()
            return
        }
        if (code == 500){
            Snackbar.make(signInContainer, getString(R.string.error_server), Snackbar.LENGTH_LONG).show()
            return
        }
        else {
            Snackbar.make(signInContainer, "Unexpected error. Response code: $code", Snackbar.LENGTH_LONG).show()
            return
        }
    }

}