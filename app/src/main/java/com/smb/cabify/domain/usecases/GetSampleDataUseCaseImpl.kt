package com.smb.cabify.domain.usecases

import com.smb.cabify.domain.model.SampleChildModel
import com.smb.cabify.domain.repository.SampleDataRepository
import com.smb.core.data.Result

class GetSampleDataUseCaseImpl(
    private val repository: SampleDataRepository
) : GetSampleDataUseCase {

    override suspend fun invoke(params: Unit): Result<SampleChildModel> =
        repository.getSampleData()
}