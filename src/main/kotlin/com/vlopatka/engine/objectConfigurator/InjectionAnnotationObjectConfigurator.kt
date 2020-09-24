package com.vlopatka.engine.objectConfigurator

import com.vlopatka.annotation.Injection
import com.vlopatka.engine.ObjectFactory
import com.vlopatka.engine.helper.FieldHelper.setValue
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

class InjectionAnnotationObjectConfigurator : ObjectConfigurator {

    @ExperimentalStdlibApi
    override fun configure(obj: Any) {
        for (field in obj::class.memberProperties) {

            field.takeIf { it.hasAnnotation<Injection>() }?.let {
                val property = obj::class.memberProperties.find { it.name == field.name }!!

                setValue(
                    thObject = obj,
                    field = field,
                    value = ObjectFactory.createObject(property.returnType.javaType as Class<*>)
                )
            }
        }
    }
}