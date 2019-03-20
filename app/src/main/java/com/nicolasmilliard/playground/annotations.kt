package com.nicolasmilliard.playground

import javax.inject.Qualifier
import javax.inject.Scope

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE, AnnotationTarget.PROPERTY,
    AnnotationTarget.FIELD
)
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Root

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class Screen

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope