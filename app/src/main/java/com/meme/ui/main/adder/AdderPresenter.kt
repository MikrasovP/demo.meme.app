package com.meme.ui.main.adder

import android.text.Editable
import com.meme.R
import com.meme.model.dto.MemeDto
import com.meme.model.repo.DatabaseRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
        DatabaseRepo.insert(MemeDto(
            title = activity.getHeaderText(),
            description = activity.getMainText(),
            isFavourite = false,
            photoUrl = pictureUrl,
            createdDate = Calendar.getInstance().timeInMillis
        ))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                activity.finish()
            }
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