package com.meme.utils

import com.meme.model.database.MemeEntity
import com.meme.model.dto.MemeDto

class MemeConverter {
    fun fromEntityToDto(memeEntity: MemeEntity?): MemeDto? {
        if (memeEntity == null)
            return null

        return MemeDto(
            id = memeEntity.id,
            title = memeEntity.title,
            createdDate = memeEntity.createdDate,
            photoUrl = memeEntity.photoUrl,
            description = memeEntity.description,
            isFavourite = memeEntity.isFavourite
        )
    }

    fun fromDtoToEntity(memeDto: MemeDto?): MemeEntity? {
        if (memeDto == null)
            return null

        return MemeEntity(
            id = memeDto.id,
            title = memeDto.title,
            createdDate = memeDto.createdDate,
            photoUrl = memeDto.photoUrl,
            description = memeDto.description,
            isFavourite = memeDto.isFavourite
        )
    }

    fun fromEntityToDtoNonNull(memeEntity: MemeEntity): MemeDto {
        return MemeDto(
            id = memeEntity.id,
            title = memeEntity.title,
            createdDate = memeEntity.createdDate,
            photoUrl = memeEntity.photoUrl,
            description = memeEntity.description,
            isFavourite = memeEntity.isFavourite
        )
    }

    fun fromDtoToEntityNonNull(memeDto: MemeDto): MemeEntity {
        return MemeEntity(
            id = memeDto.id,
            title = memeDto.title,
            createdDate = memeDto.createdDate,
            photoUrl = memeDto.photoUrl,
            description = memeDto.description,
            isFavourite = memeDto.isFavourite
        )
    }

    fun fromEntityListToDto(list: List<MemeEntity>): List<MemeDto> {
        val memesDto: MutableList<MemeDto> = mutableListOf()
        for (item in list) {
            memesDto.add(fromEntityToDtoNonNull(item))
        }
        return memesDto.toList()
    }

    fun fromDtoListToEntity(list: List<MemeDto>): List<MemeEntity> {
        val memesDto: MutableList<MemeEntity> = mutableListOf()
        for (item in list) {
            memesDto.add(fromDtoToEntityNonNull(item))
        }
        return memesDto.toList()
    }
}