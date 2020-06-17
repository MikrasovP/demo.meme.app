package com.meme.model.database

import androidx.room.*

@Dao()
interface MemeDao{

    @Query("SELECT * FROM MemeEntity")
    fun getMemes() : List<MemeEntity>

    @Query("SELECT * FROM MemeEntity WHERE id = :id")
    fun getById(id: Long): MemeEntity?

    @Insert
    fun insert(meme: MemeEntity)

    @Update
    fun update(meme: MemeEntity)

    @Delete
    fun delete(meme: MemeEntity)

}