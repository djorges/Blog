package com.example.blog.exception


class EntityNotFoundException: RuntimeException
{
    constructor(message:String) : super(message = message)
    constructor(entityName:String, fieldName:String) : super(message = "$entityName not found by: $fieldName")
    constructor(entityName:String, fieldName:String, fieldValue:Int) : super(message = "$entityName not found by: $fieldName : $fieldValue")
}