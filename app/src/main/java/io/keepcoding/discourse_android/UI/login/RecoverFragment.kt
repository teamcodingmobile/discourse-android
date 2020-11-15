package io.keepcoding.discourse_android.UI.login

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import io.keepcoding.discourse_android.Data.Models.AppModels.ResetPasswordModel
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.inflate
import kotlinx.android.synthetic.main.recover_fragment.*
import kotlinx.android.synthetic.main.recover_fragment.createText
import kotlinx.android.synthetic.main.signin_fragment.*
import java.lang.IllegalArgumentException

class RecoverFragment: Fragment() {

    var recoverInteractionListener: RecoverInteractionListener? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.recover_fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RecoverInteractionListener)
            recoverInteractionListener = context
        else
            throw IllegalArgumentException("Context doesn't implement ${RecoverInteractionListener::class.java.canonicalName}")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        back_login_button.setOnClickListener(){
            recoverInteractionListener?.onBackToLogin()
        }

        email_button.setOnClickListener(){
            if (isFormValid()){
                val form = ResetPasswordModel(
                        inputRecoverUsername.text.toString()
                )
                recoverInteractionListener?.onSendEmail(form)
            } else {
                showErrors()
            }

        }

    }

    private fun init(){
        val myCustomFontBold : Typeface? = ResourcesCompat.getFont(requireContext(), R.font.avenir_next_bold)
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(requireContext(), R.font.avenir_next_regular)
        createText.typeface = myCustomFontBold
        inputRecoverUsername.typeface = myCustomFontRegular
        email_button.typeface = myCustomFontRegular
    }

    private fun isFormValid(): Boolean {
        return inputRecoverUsername.text.isNotEmpty()
    }

    private fun showErrors(){
        if (inputRecoverUsername.text.isEmpty())
            inputRecoverUsername.error = getString(R.string.error_empty)
    }

    interface RecoverInteractionListener {
        fun onBackToLogin()
        fun onSendEmail(form: ResetPasswordModel)
    }

}