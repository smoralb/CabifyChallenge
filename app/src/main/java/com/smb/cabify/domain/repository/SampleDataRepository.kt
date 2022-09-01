package com.smb.cabify.domain.repository

import com.smb.cabify.domain.model.SampleChildModel
import com.smb.core.data.Result

interface SampleDataRepository {

    suspend fun getSampleData(): Result<SampleChildModel>
}