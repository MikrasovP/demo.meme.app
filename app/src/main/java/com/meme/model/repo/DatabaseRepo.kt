package com.meme.model.repo

import com.meme.model.database.AppDatabase
import com.meme.model.dto.MemeDto
import com.meme.utils.App
import com.meme.utils.MemeConverter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

object DatabaseRepo {
    private val db: AppDatabase by lazy {
        App.appInstance.getDatabase()
    }

    fun getMeme(id: Long): Observable<MemeDto?> =
        Observable.fromCallable {
            MemeConverter.fromEntityToDto(db.memeDao().getById(id))
        }

    fun getMemes(): Observable<List<MemeDto>> =
        Observable.fromCallable {
            MemeConverter.fromEntityListToDto(db.memeDao().getMemes())
        }

    fun insert(meme: MemeDto): Completable =
        Completable.fromCallable {
            db.memeDao().insert(
                MemeConverter.fromDtoToEntityNonNull(meme)
            )
        }

    fun update(meme: MemeDto): Completable =
        Completable.fromCallable {
            db.memeDao().update(
                MemeConverter.fromDtoToEntityNonNull(meme)
            )
        }

    fun delete(meme: MemeDto): Completable =
        Completable.fromCallable {
            db.memeDao().update(
                MemeConverter.fromDtoToEntityNonNull(meme)
            )
        }
}