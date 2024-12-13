package com.kaz.post.infrastructure.configuration

import com.kaz.post.domain.model.ErrorResponseParcel
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.function.client.WebClientResponseException

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(WebClientResponseException.BadRequest::class)
    fun handleBadRequestException(
        ex: WebClientResponseException.BadRequest,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponseParcel> {
        val errorResponse = ErrorResponseParcel(
            status = ex.rawStatusCode,
            message = ex.message ?: "Bad Request",
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}