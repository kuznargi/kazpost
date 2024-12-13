package com.kaz.post.application.usecase

import com.kaz.post.domain.model.RequestParcel
import com.kaz.post.domain.model.ResponseParcel
import com.kaz.post.infrastructure.repository.ExternalParcelApiRepository
import org.springframework.stereotype.Service

@Service
class SendParcelUseCase(private val repository: ExternalParcelApiRepository){
    fun execute(request: RequestParcel): ResponseParcel {
        val response =repository.sendParcel(request)
        return response
    }
}