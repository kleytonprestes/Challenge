package com.br.myapplication

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var passwordET : AppCompatEditText? = null
    private var usernameET : AppCompatEditText? = null
    private var loginButton : AppCompatButton? = null
    private var loginSuccess : AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        passwordET = findViewById(R.id.passwordET)
        usernameET = findViewById(R.id.usernameET)
        loginButton = findViewById(R.id.loginButton)
        loginSuccess = findViewById(R.id.loginSuccessfully)

        loginButton?.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                async {
                    callLogin()
                }.await()
            }
        }
    }

    private fun callLogin(){
        val password = passwordET?.text
        val username = usernameET?.text
        if(password.isNullOrBlank() ) {
            passwordET?.error = "Password Invalid"
        } else  if (username.isNullOrBlank()){
            usernameET?.error = "User Invalid"
        } else {
            loginSuccess?.visibility = VISIBLE
        }
    }
}