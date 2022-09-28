package com.majd.techestisharatiassignment.ui.activities.login

import com.majd.techestisharatiassignment.models.UserModel

interface LoginView {
    fun onLoadingLogin()
    fun onLoginSucceeded(user: UserModel)
    fun onLoginFailed()
}