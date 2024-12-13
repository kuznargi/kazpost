package com.kaz.post.adapter.web

import com.kaz.post.application.usecase.SendParcelUseCase
import com.kaz.post.domain.model.ErrorResponseParcel
import com.kaz.post.domain.model.RequestParcel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(private val sendParcelUseCase: SendParcelUseCase){

    @PostMapping("/send/post/request")
    fun sendPostRequest(@RequestBody request: RequestParcel): ResponseEntity<Any> {
        return try {
            val response = sendParcelUseCase.execute(request)
            ResponseEntity.ok(response)
        } catch (ex: IllegalArgumentException) {
            val errorResponse = ErrorResponseParcel(
                status = HttpStatus.BAD_REQUEST.value(),
                message = ex.message ?: "Invalid data"
            )
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        }
    }

}