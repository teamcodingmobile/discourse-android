package io.keepcoding.discourse_android.UI.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.TabsActivity
import kotlinx.android.synthetic.main.signup_activity.*


class SignUpActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        init()

        back_button.setOnClickListener(){
            finish()
        }

        register_button.setOnClickListener(){
            val intent = Intent(this, TabsActivity::class.java)
            startActivity(intent)
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




}