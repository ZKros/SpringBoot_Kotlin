package com.acme.tour.advice

import com.acme.tour.exception.PromoçãoNotFounException
import com.acme.tour.model.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonFormatExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Json Inválido", exception.message ?: "Inválido Json"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PromoçãoNotFounException::class)
    fun PromoçãoNotFounExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
                                        exception: Exception): ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Promoção não localizada", exception.message !!), HttpStatus.NOT_FOUND)
    }
}

