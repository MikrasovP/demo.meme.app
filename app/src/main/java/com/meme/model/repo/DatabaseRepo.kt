package com.meme.model.repo

import com.meme.model.database.AppDatabase
import com.meme.model.dto.MemeDto
import com.meme.utils.App
import com.meme.utils.MemeConverter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DatabaseRepo(
    private val db: AppDatabase,
    private val memeConverter: MemeConverter
) {

    fun getMeme(id: Long): Observable<MemeDto?> =
        Observable.fromCallable {
            memeConverter.fromEntityToDto(db.memeDao().getById(id))
        }

    fun getMemes(): Observable<List<MemeDto>> =
        Observable.fromCallable {
            memeConverter.fromEntityListToDto(db.memeDao().getMemes())
        }

    fun insert(meme: MemeDto): Completable =
        Completable.fromCallable {
            db.memeDao().insert(
                memeConverter.fromDtoToEntityNonNull(meme)
            )
        }

    fun update(meme: MemeDto): Completable =
        Completable.fromCallable {
            db.memeDao().update(
                memeConverter.fromDtoToEntityNonNull(meme)
            )
        }

    fun delete(meme: MemeDto): Completable =
        Completable.fromCallable {
            db.memeDao().update(
                memeConverter.fromDtoToEntityNonNull(meme)
            )
        }
}