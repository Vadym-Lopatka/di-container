package com.vlopatka.reflection.objectConfigurator

import com.vlopatka.annotation.InjectProperty
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class InjectPropertyAnnotationObjectConfigurator : ObjectConfigurator {

    private val propertiesMap: Map<String, String> = getFileAsMap("application.properties")

    override fun configure(obj: Any) {
        for (field in obj::class.memberProperties) {
            if (field.hasAnnotation<InjectProperty>()) {
                val theAnnotation = field.annotations.find { it is InjectProperty } as InjectProperty
                val valueForInjection = theAnnotation.value.takeIf { it.isNotEmpty() } ?: propertiesMap[field.name]

                setValueToField(obj, field, valueForInjection)
            }
        }
    }

    private fun setValueToField(thObject: Any, field: KProperty1<out Any, *>, value: String?) {
        val property = thObject::class.memberProperties.find { it.name == field.name }
        if (property is KMutableProperty<*>) {
            property.isAccessible = true
            property.setter.call(thObject, value)
        }
    }

    private fun getFileAsMap(filename: String): Map<String, String> {
        return ClassLoader.getSystemClassLoader().getResource(filename)
            .readText()
            .lines()
            .map { it.split("=") }
            .associateBy({ it[0] }, { it[1] })
    }
}