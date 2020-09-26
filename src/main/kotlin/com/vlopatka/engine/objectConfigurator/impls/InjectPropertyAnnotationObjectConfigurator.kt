package com.vlopatka.engine.objectConfigurator.impls

import com.vlopatka.engine.annotation.InjectProperty
import com.vlopatka.engine.context.ApplicationContext
import com.vlopatka.engine.helper.FieldHelper.setValueToObject
import com.vlopatka.engine.objectConfigurator.ObjectConfigurator
import kotlin.reflect.KProperty1
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

class InjectPropertyAnnotationObjectConfigurator : ObjectConfigurator {

    private val propertiesMap: Map<String, String> = getFileAsMap("application.properties")

    override fun configure(obj: Any, context: ApplicationContext) {

        obj::class.memberProperties
            .filter { it.hasAnnotation<InjectProperty>() }
            .forEach { setValueToObject(obj, it, fetchValue(it)) }
    }

    private fun fetchValue(field: KProperty1<out Any, *>): String? {
        val theAnnotation = field.annotations.find { it is InjectProperty } as InjectProperty
        return theAnnotation.value.takeIf { it.isNotEmpty() } ?: propertiesMap[field.name]
    }

    private fun getFileAsMap(filename: String): Map<String, String> {
        return ClassLoader.getSystemClassLoader().getResource(filename)
            .readText()
            .lines()
            .map { it.split("=") }
            .associateBy({ it[0] }, { it[1] })
    }
}