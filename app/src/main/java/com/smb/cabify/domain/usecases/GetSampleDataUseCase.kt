package com.smb.cabify.domain.usecases

import com.smb.cabify.domain.model.SampleChildModel
import com.smb.core.domain.UseCase

interface GetSampleDataUseCase: UseCase<Unit, SampleChildModel>