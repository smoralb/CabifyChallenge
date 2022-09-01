package com.smb.core.domain

interface UseCase<in P, out M> {

    suspend operator fun invoke(params: P): com.smb.core.data.Result<M>
}