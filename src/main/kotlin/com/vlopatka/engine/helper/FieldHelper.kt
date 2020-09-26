package com.vlopatka.engine.helper

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

object FieldHelper {
    fun setValueToObject(thObject: Any, field: KProperty1<out Any, *>, value: Any?) {
        val property = thObject::class.memberProperties.find { it.name == field.name }

        if (property is KMutableProperty<*>) {
            property.isAccessible = true
            property.setter.call(thObject, value)
        }
    }
}
