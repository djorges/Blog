package com.example.blog.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler(){
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun entityNotFoundHandler(e: EntityNotFoundException): String? { return e.message }

    @ResponseBody
    @ExceptionHandler(InvalidOperationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun invalidOperationHandler(e: InvalidOperationException): String? { return e.message }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun customHandler(e: Exception): String? { return e.message }
}