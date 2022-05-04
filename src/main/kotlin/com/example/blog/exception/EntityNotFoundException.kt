package com.example.blog.exception


class EntityNotFoundException(
    entityName:String,
    fieldName:String,
    fieldValue:Int,
) : RuntimeException(message = "$entityName not found by: $fieldName : $fieldValue")