package com.vlopatka.engine.objectConfigurator.impls

import com.vlopatka.engine.annotation.Injection
import com.vlopatka.engine.context.ApplicationContext
import com.vlopatka.engine.helper.FieldHelper.setValue
import com.vlopatka.engine.objectConfigurator.ObjectConfigurator
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

class InjectionAnnotationObjectConfigurator : ObjectConfigurator {

    @ExperimentalStdlibApi
    override fun configure(obj: Any, context: ApplicationContext) {
        for (field in obj::class.memberProperties) {

            field.takeIf { it.hasAnnotation<Injection>() }?.let {
                val property = obj::class.memberProperties.find { it.name == field.name }!!

                setValue(
                    thObject = obj,
                    field = field,
                    value = context.getObject(property.returnType.javaType as Class<*>)
                )
            }
        }
    }
}