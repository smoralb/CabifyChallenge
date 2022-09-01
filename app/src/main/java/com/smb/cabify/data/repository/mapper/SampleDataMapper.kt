package com.smb.cabify.data.repository.mapper

import com.smb.cabify.data.entity.SampleApiResponseEntity
import com.smb.cabify.domain.model.SampleChildModel

interface SampleDataMapper {

    fun toDomainModel(entity: SampleApiResponseEntity?): SampleChildModel
}