package com.meme.ui.main.adder

import android.text.Editable
import com.meme.R
import com.meme.database.MemeEntity
import com.meme.utils.App
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class AdderPresenter(
    private val activity: AdderActivity
) {

    private var isHeaderEmpty: Boolean = true
    private var isMainEmpty: Boolean = true
    private var isImageEmpty: Boolean = true

    private lateinit var pictureUrl: String

    private fun checkAddBtn() {
        if (!isMainEmpty && !isHeaderEmpty && !isImageEmpty)
            activity.showCreateBtn()
        else
            activity.hideCreateBtn()
    }

    fun onHeaderTextEdited(it: Editable?) {
        isHeaderEmpty = it.isNullOrEmpty()
        checkAddBtn()

        if (it != null
            && it.length >= activity.resources.getInteger(R.integer.adder_header_max_char)
        ) {
            activity.showTextExcessError()
        }
    }

    fun onMainTextEdited(it: Editable?) {
        isMainEmpty = it.isNullOrEmpty()
        checkAddBtn()

        if (it != null
            && it.length >= activity.resources.getInteger(R.integer.adder_main_max_char)
        ) {
            activity.showTextExcessError()
        }
    }

    fun onAddBtnClicked(){
        val db = App.appInstance.getDatabase()

        Observable.just{
            db.memeDao().insert(MemeEntity(
                title = activity.getHeaderText(),
                description = activity.getMainText(),
                isFavourite = false,
                photoUrl = pictureUrl,
                createdDate = Calendar.getInstance().timeInMillis
            ))
        }.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe{
                it.invoke()
            }

        activity.finish()
    }

    /**
     * TODO: implement image picking
     * Now this method insert default image
     */
    fun onPictureBtnClicked() {
        pictureUrl = "https://i1.photo.2gis.com/images/profile/30258560046984878_e1da_640x.jpg"
        activity.addPicture(pictureUrl)

        isImageEmpty = false
        activity.hidePictureBtn()
        checkAddBtn()
    }

    fun onCloseBtnClickListener() {
        activity.finish()
    }
}