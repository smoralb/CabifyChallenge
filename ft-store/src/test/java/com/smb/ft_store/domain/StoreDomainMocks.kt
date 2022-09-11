package com.smb.ft_store.domain

import com.smb.core.domain.model.ProductRequest
import com.smb.core.extensions.DEFAULT_FLOAT
import com.smb.core.extensions.DEFAULT_INT
import com.smb.core.extensions.EMPTY_STRING

internal val productRequestDomainMock = ProductRequest(
    id = EMPTY_STRING,
    name = EMPTY_STRING,
    image = EMPTY_STRING,
    price = DEFAULT_FLOAT,
    quantity = DEFAULT_INT
)