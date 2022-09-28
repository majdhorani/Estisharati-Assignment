package com.majd.techestisharatiassignment.ui.activities.login

import com.majd.techestisharatiassignment.models.UserModel

class LoginPresenter constructor(val view: LoginView) {

    fun login(email: String, password: String) {
        view.apply {
            // Indicate ui as loading
            onLoadingLogin()

            // Send login request
            // ...

            // Handle response
            onLoginSucceeded(UserModel(email, password))
        }
    }
}