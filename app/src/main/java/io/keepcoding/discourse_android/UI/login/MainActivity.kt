package io.keepcoding.discourse_android.UI.login

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint.UNDERLINE_TEXT_FLAG
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import io.keepcoding.discourse_android.Data.LoginService
import io.keepcoding.discourse_android.R
import io.keepcoding.discourse_android.UI.TabsActivity
import io.keepcoding.discourse_android.isFirstTimeCreated
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity : AppCompatActivity(){

    private val textList = arrayOf("community","team","customers","fans")
    private var counter = 0
    private val timer = Timer()
    private val loginService = LoginService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (isFirstTimeCreated(savedInstanceState)) {
            checkSession()
        }
        init()

        create_account_button.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }

        login_button.setOnClickListener(){
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkSession() {
        if (loginService.isLogged(this.applicationContext)) {
            goToTopics()
        }
    }


    fun init(){
        val myCustomFontRegular : Typeface? = ResourcesCompat.getFont(this, R.font.avenir_next_regular)
        main_text.typeface = myCustomFontRegular

        text_switcher.setFactory {
            val textView = TextView(this@MainActivity)
            textView.textSize = 32f
            textView.setTextColor(Color.parseColor("#191919"))
            textView.setPaintFlags(UNDERLINE_TEXT_FLAG)
            textView.typeface = myCustomFontRegular

            textView
        }


        timer.schedule(object : TimerTask() {
            override fun run() {
                this@MainActivity.runOnUiThread{
                    if (counter == textList.size){
                        counter = 0
                    }
                    text_switcher.setText(textList[counter])
                    counter += 1
                }

            }
        }, 100, 1000)

    }

    private fun goToTopics(){
        val intent = Intent(this, TabsActivity::class.java)
        startActivity(intent)
    }

}