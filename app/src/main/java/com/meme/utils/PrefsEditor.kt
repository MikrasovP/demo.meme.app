package com.meme.utils

import android.content.Context
import android.content.ContextWrapper
import com.meme.model.UserInfo
import com.meme.model.dto.AuthInfoDto
import com.pixplicity.easyprefs.library.Prefs

class PrefsEditor {
    companion object{
        private const val TOKEN_KEY = "token"
        private const val USER_ID_KEY = "id"
        private const val USERNAME_KEY = "username"
        private const val FIRST_NAME_KEY = "firstName"
        private const val LAST_NAME_KEY = "lastName"
        private const val USER_DESCRIPTION_KEY = "userDescription"
    }

    /**
     * This method should be invoked after application start (in App class, for example)
     */
    fun build(context: Context, packageName: String){
        Prefs.Builder()
            .setContext(context)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    fun setUser(authInfoDto: AuthInfoDto){
        Prefs.putString(TOKEN_KEY, authInfoDto.accessToken)
        Prefs.putInt(USER_ID_KEY, authInfoDto.userInfo.id)
        Prefs.putString(USERNAME_KEY, authInfoDto.userInfo.username)
        Prefs.putString(FIRST_NAME_KEY, authInfoDto.userInfo.firstName)
        Prefs.putString(LAST_NAME_KEY, authInfoDto.userInfo.lastName)
        Prefs.putString(USER_DESCRIPTION_KEY, authInfoDto.userInfo.userDescription)
    }

    fun getUser() : UserInfo = UserInfo(
        id = Prefs.getInt(USER_ID_KEY, -1),
        username = Prefs.getString(USERNAME_KEY, ""),
        firstName = Prefs.getString(FIRST_NAME_KEY, ""),
        lastName = Prefs.getString(LAST_NAME_KEY, ""),
        userDescription = Prefs.getString(USER_DESCRIPTION_KEY, "")
    )

    fun removeUser(){
        Prefs.remove(TOKEN_KEY)
        Prefs.remove(USER_ID_KEY)
        Prefs.remove(USERNAME_KEY)
        Prefs.remove(FIRST_NAME_KEY)
        Prefs.remove(LAST_NAME_KEY)
        Prefs.remove(USER_DESCRIPTION_KEY)
    }
}