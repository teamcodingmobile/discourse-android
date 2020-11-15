package io.keepcoding.discourse_android.UI.login

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import io.keepcoding.discourse_android.Data.Models.AppModels.SignInModel
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.inflate
import kotlinx.android.synthetic.main.signin_fragment.*
import java.lang.IllegalArgumentException

class SignInFragment: Fragment() {

    var signInInteractionListener: SignInInteractionListener? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.signin_fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SignInInteractionListener)
            signInInteractionListener = context
        else
            throw IllegalArgumentException("Context doesn't implement ${SignInInteractionListener::class.java.canonicalName}")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        login_button.setOnClickListener(){
            if (isFormValid()){
                var form = SignInModel(
                        inputSignInUsername.text.toString(),
                        inputSignInPassword.text.toString()
                )
                signInInteractionListener?.onLogin(form)
            } else {
                showErrors()
            }
        }

        recover_button.setOnClickListener(){
            signInInteractionListener?.onRecoverPassword()
        }

        }

    private fun init(){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(requireContext(), R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(requireContext(), R.font.avenir_next_regular)
        createText.typeface = myCustomFontBold
        inputSignInUsername.typeface = myCustomFontRegular
        inputSignInPassword.typeface = myCustomFontRegular
        login_button.typeface = myCustomFontRegular
    }

    interface SignInInteractionListener {
        fun onLogin(form: SignInModel)
        fun onRecoverPassword()
    }

    private fun isFormValid(): Boolean {
        return inputSignInUsername.text.isNotEmpty() && inputSignInPassword.text.isNotEmpty()
    }

    private fun showErrors(){
        if (inputSignInUsername.text.isEmpty())
            inputSignInUsername.error = getString(R.string.error_empty)

        if (inputSignInPassword.text.isEmpty())
            inputSignInPassword.error = getString(R.string.error_empty)
    }


}