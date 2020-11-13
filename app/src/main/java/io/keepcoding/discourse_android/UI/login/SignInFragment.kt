package io.keepcoding.discourse_android.UI.login

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
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
            signInInteractionListener?.onLogin()
        }

        recover_button.setOnClickListener(){
            signInInteractionListener?.onRecoverPassword()
        }

        }

    private fun init(){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(requireContext(), R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(requireContext(), R.font.avenir_next_regular)
        createText.typeface = myCustomFontBold
        inputSignUpUsername.typeface = myCustomFontRegular
        inputSignUpPassword.typeface = myCustomFontRegular
        login_button.typeface = myCustomFontRegular
    }

    interface SignInInteractionListener {
        fun onLogin()
        fun onRecoverPassword()
    }


}