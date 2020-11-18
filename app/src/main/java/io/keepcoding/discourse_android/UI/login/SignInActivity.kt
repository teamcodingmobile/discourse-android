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
import io.keepcoding.discourse_android.Data.Models.AppModels.ResetPasswordModel
import io.keepcoding.discourse_android.Data.Models.AppModels.SignInModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.ResetPasswordResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignInResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignUpResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.TabsActivity
import kotlinx.android.synthetic.main.signin_activity.*
import kotlinx.android.synthetic.main.signin_activity.back_button
import kotlinx.android.synthetic.main.signin_activity.container
import kotlinx.android.synthetic.main.signin_activity.viewLoading
import kotlinx.android.synthetic.main.signin_fragment.*
import retrofit2.Response

class SignInActivity : AppCompatActivity(),
        SignInFragment.SignInInteractionListener, RecoverFragment.RecoverInteractionListener{

    private val mViewModel: SignInViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(SignInViewModel::class.java)
    }

    var loginService: LoginService? = null

    val signInFragment = SignInFragment()
    val recoverFragment = RecoverFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginService = LoginService(applicationContext)
        setContentView(R.layout.signin_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, signInFragment)
                .commit()


        back_button.setOnClickListener(){
            finish()
        }


    }

    override fun onLogin(form: SignInModel) {
        enableLoading()
        mViewModel.login(object: DiscourseService.CallbackResponse<SignInResponse>{
            override fun onResponse(response: SignInResponse) {
                enableLoading(false)

                val userId = response.user?.id

                if (null == userId) {
                    handleError(0)
                }

                handleLoginResponse(id = userId!!, username = inputSignInUsername.text.toString())
            }

            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                enableLoading(false)
                handleError(code)
            }

        }, username = form.username)
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

    override fun onSendEmail(form: ResetPasswordModel) {
        enableLoading()
        mViewModel.resetPassword(object: DiscourseService.CallbackResponse<ResetPasswordResponse>{
            override fun onResponse(response: ResetPasswordResponse) {
                enableLoading(false)
                handleResetResponse(response)
            }
            override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                enableLoading(false)
                Snackbar.make(container, "Error, Status code: $code", Snackbar.LENGTH_LONG).show()
            }

        },  form = form)
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

    private fun handleLoginResponse(id: Int, username: String){
            loginService?.saveSession(id, username)
            val intent = Intent(this, TabsActivity::class.java)
            startActivity(intent)
    }

    private fun handleResetResponse(response: ResetPasswordResponse){
        //TODO - falta probarlo
        if (response.userFound == true){
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, signInFragment)
                    .commit()
            Snackbar.make(container, getString(R.string.reset_success), Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(container, getString(R.string.reset_failure), Snackbar.LENGTH_LONG).show()
        }
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