package io.keepcoding.discourse_android.UI.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.keepcoding.discourse_android.CustomViewModelFactory
import io.keepcoding.discourse_android.Data.Client.Http.DiscourseService
import io.keepcoding.discourse_android.Data.LoginService
import io.keepcoding.discourse_android.Data.Models.AppModels.SignUpModel
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SignUpResponse
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.TabsActivity
import kotlinx.android.synthetic.main.signup_activity.*
import kotlinx.android.synthetic.main.signup_activity.back_button
import retrofit2.Response

public const val FROM = "FROM"
public const val SIGN_UP = "SIGN_UP"

class SignUpActivity : AppCompatActivity(){

    private val mViewModel: SignUpViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(SignUpViewModel::class.java)
    }

    val loginService = LoginService(application)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()

        back_button.setOnClickListener(){
            finish()
        }

        register_button.setOnClickListener(){
            if (isFormValid()) {
                enableLoading()
                var signUpModel = SignUpModel(
                        inputSignUpName.text.toString(),
                        inputSignUpUsername.text.toString(),
                        inputSignUpEmail.text.toString(),
                        inputSignUpPassword.text.toString()
                )

                mViewModel.signUp(object: DiscourseService.CallbackResponse<SignUpResponse>{
                    override fun onResponse(response: SignUpResponse) {
                        enableLoading(false)
                        handleResponse(response, username = inputSignUpUsername.text.toString())
                    }
                    override fun onFailure(t: Throwable, res: Response<*>?, code: Int) {
                        enableLoading(false)
                        throw (t)
                    }



                },  form = signUpModel)

            } else {
                enableLoading(false)
                showErrors()
            }
        }

    }

    fun init(){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_regular)
        createText.typeface = myCustomFontBold
        inputSignUpUsername.typeface = myCustomFontRegular
        inputSignUpEmail.typeface = myCustomFontRegular
        inputSignUpName.typeface = myCustomFontRegular
        inputSignUpPassword.typeface = myCustomFontRegular
        register_button.typeface = myCustomFontRegular

    }

    private fun isFormValid(): Boolean {
            return inputSignUpEmail.text.isNotEmpty()
                    && inputSignUpUsername.text.isNotEmpty()
                    && inputSignUpPassword.text.isNotEmpty()
                    && inputSignUpName.text.isNotEmpty()
                    && (inputSignUpPassword.text.length >= 10)
    }

    private fun showErrors() {
            if (inputSignUpEmail.text.isEmpty())
                inputSignUpEmail.error = getString(R.string.error_empty)

            if (inputSignUpUsername.text.isEmpty())
                inputSignUpUsername.error = getString(R.string.error_empty)

            if (inputSignUpPassword.text.length < 10)
            inputSignUpPassword.error = getString(R.string.error_short)

            if (inputSignUpPassword.text.isEmpty())
                inputSignUpPassword.error = getString(R.string.error_empty)

            if (inputSignUpName.text.isEmpty())
                inputSignUpName.error = getString(R.string.error_empty)

    }

    private fun enableLoading(enabled: Boolean = true)  {
        if (enabled) {
            fragmentContainer.visibility = View.INVISIBLE
            viewLoading.visibility = View.VISIBLE
        } else {
            fragmentContainer.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }

    }

    fun handleResponse(response: SignUpResponse, username: String) {
        val success = response.success!!
         if (success) {
             val intent = Intent(this, TabsActivity::class.java)
             loginService.saveSession(response.userId!!, username)
             intent.putExtra(FROM, SIGN_UP)
             startActivity(intent)
         } else {
             Snackbar.make(container, response.message.toString(), Snackbar.LENGTH_LONG).show()
         }
    }

}