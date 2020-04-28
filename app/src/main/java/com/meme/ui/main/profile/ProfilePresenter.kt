package com.meme.ui.main.profile

import com.meme.utils.PrefsEditor

class ProfilePresenter(
    private val fragment: ProfileFragment
) {

    fun onActivityCreated(){
        fragment.setUserData(PrefsEditor.getUser())
    }

}