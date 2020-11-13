package io.keepcoding.discourse_android.UI.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.TabsActivity
import kotlinx.android.synthetic.main.signin_activity.*

class SignInActivity : AppCompatActivity(),
        SignInFragment.SignInInteractionListener, RecoverFragment.RecoverInteractionListener{

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

    override fun onLogin() {
        val intent = Intent(this, TabsActivity::class.java)
        startActivity(intent)
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
        //llamar a la api y volver a main
    }


}