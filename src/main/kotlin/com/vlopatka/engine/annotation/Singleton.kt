package com.vlopatka.engine.annotation

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Singleton
