package com.example.blog.exception

class InvalidOperationException: RuntimeException{
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}