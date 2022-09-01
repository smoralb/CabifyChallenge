package com.smb.cabify.data.source

import com.smb.cabify.domain.model.SampleChildModel
import com.smb.core.data.Result

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<SampleChildModel>
}