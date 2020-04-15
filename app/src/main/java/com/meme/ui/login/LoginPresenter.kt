package com.meme.ui.login

import com.meme.model.UserInfo
import com.meme.model.service.MemesNetworkService

class LoginPresenter(val activity: LoginActivity) {
    private val service = MemesNetworkService

    fun auth(login: String, password: String) {
        println(service.auth(login, password).toString())
    }
}