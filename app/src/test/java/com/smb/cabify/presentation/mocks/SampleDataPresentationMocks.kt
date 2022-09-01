package com.smb.cabify.presentation.mocks

import com.smb.cabify.domain.model.SampleChildDetailsModel
import com.smb.cabify.domain.model.SampleChildModel

const val SAMPLE_TITLE = "SAMPLE TITLE"
private const val SAMPLE_DESCRIPTION = "SAMPLE DESCRIPTION"
private const val SAMPLE_PUBLISHER = "SAMPLE PUBLISHER"
private const val SAMPLE_ISBN = "SAMPLE ISBN"

/**
 * MODEL
 */

//region Valid Models

internal val sampleResponseChildDetailsModelMock =
    SampleChildDetailsModel(
        title = SAMPLE_TITLE,
        description = SAMPLE_DESCRIPTION,
        publisher = SAMPLE_PUBLISHER,
        isbn = SAMPLE_ISBN
    )

internal val sampleResponseChildModelMock = SampleChildModel(
    bookDetails = listOf(
        sampleResponseChildDetailsModelMock
    )
)

//endregion
