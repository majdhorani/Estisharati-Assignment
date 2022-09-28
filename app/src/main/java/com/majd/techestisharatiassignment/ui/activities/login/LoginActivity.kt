package com.majd.techestisharatiassignment.ui.activities.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.models.UserModel
import com.majd.techestisharatiassignment.ui.activities.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenters()
        initListeners()
    }

    // Initiate presenters
    private fun initPresenters() {
        // Init login presenter
        presenter = LoginPresenter(this)
    }

    // Initiate actions listeners
    private fun initListeners() {
        login_button.setOnClickListener {
            // Send login request
            presenter.login(
                email = email.text.toString(),
                password = password.text.toString()
            )
        }
    }

    // Callback will be called when request is being sent
    override fun onLoadingLogin() {

    }

    // Callback will be called when login request success
    override fun onLoginSucceeded(user: UserModel) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // Callback will be called when login request fail
    override fun onLoginFailed() {
        // Show error message
    }
}