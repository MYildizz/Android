package com.example.foursquarekotlin

import android.content.Intent
import android.icu.text.TimeZoneFormat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.parse.*
import kotlinx.android.synthetic.main.activity_main.*

class SingInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ParseAnalytics.trackAppOpenedInBackground(intent)

    }

    fun signIn(view : View)
    {
        ParseUser.logInInBackground(usernameText.text.toString(),passwordText.text.toString(), LogInCallback { user, e ->

            if(e != null)
            {
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()

            }
            else
            {
                Toast.makeText(applicationContext,"Welcome "+user.username.toString(),Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, LocationsActivity::class.java)
                startActivity(intent)
            }

        })
        
    }

    fun signUp(view: View)
    {
        val user =ParseUser()
        user.username=usernameText.text.toString()

        user.setPassword(passwordText.text.toString())

        user.signUpInBackground { e ->

            if(e != null)
            {
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()

            }
            else
            {
                Toast.makeText(applicationContext,"user created",Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, LocationsActivity::class.java)
                startActivity(intent)

            }
        }


    }
}
