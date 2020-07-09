package com.meme.ui.main.profile

import com.meme.model.repo.DatabaseRepo
import com.meme.model.repo.MemesNetRepo
import com.meme.utils.App
import com.meme.utils.PrefsEditor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter(
    private val fragment: ProfileFragment
) {

    @Inject
    lateinit var databaseRepo: DatabaseRepo

    @Inject
    lateinit var prefsEditor: PrefsEditor

    fun onActivityCreated() {
        val app: App = fragment.requireActivity().application as App
        app.appComponent.inject(this)

        fragment.setUserData(prefsEditor.getUser())
    }

    private fun loadMemes() {
        databaseRepo.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                fragment.hideProgressBar()
                fragment.showMemes(it)
            }, {
                throw it
            })
    }

    fun refreshMemes() = loadMemes()

    fun getMemes() {
        fragment.showProgressBar()

        loadMemes()
    }

    fun onLogoutClick() {
        fragment.showAlertDialog()
    }

    fun logout() {
        MemesNetRepo.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                prefsEditor.removeUser()
                fragment.activity?.finish()
                fragment.moveToLoginActivity()
            }, {
                throw it
            })

    }

}