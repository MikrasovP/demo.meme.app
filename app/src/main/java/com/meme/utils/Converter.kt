package com.meme.utils

import com.meme.database.MemeEntity
import com.meme.model.dto.MemeDto

object Converter {
    fun fromEntityToDto(memeEntity: MemeEntity) : MemeDto{
        return MemeDto(
            id = memeEntity.id,
            title = memeEntity.title,
            createdDate = memeEntity.createdDate,
            photoUrl = memeEntity.photoUrl,
            description = memeEntity.description,
            isFavourite = memeEntity.isFavourite
        )
    }

    fun fromDtoToEntity(memeDto: MemeDto) : MemeEntity{
        return MemeEntity(
            id = memeDto.id,
            title = memeDto.title,
            createdDate = memeDto.createdDate,
            photoUrl = memeDto.photoUrl,
            description = memeDto.description,
            isFavourite = memeDto.isFavourite
        )
    }
}