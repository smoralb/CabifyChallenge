package com.smb.cabify.data.repository

import com.smb.cabify.data.source.SampleDataRemoteSource
import com.smb.cabify.domain.model.SampleChildModel
import com.smb.cabify.domain.repository.SampleDataRepository
import com.smb.core.data.Result

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<SampleChildModel> = remoteSource.getSampleData()

}