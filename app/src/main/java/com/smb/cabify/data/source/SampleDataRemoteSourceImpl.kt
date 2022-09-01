package com.smb.cabify.data.source

import com.smb.cabify.data.SampleApi
import com.smb.cabify.data.repository.mapper.SampleDataMapper
import com.smb.cabify.domain.model.SampleChildModel
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall

class SampleDataRemoteSourceImpl(
    private val api: SampleApi,
    private val mapper: SampleDataMapper) : SampleDataRemoteSource {

    override suspend fun getSampleData(): Result<SampleChildModel> {
        return safeApiCall(
            { api.getSampleData() },
            { entity -> mapper.toDomainModel(entity) }
        )
    }
}