package com.vlopatka.engine.objectConfigurator.impls

import com.vlopatka.engine.annotation.Injection
import com.vlopatka.engine.context.ApplicationContext
import com.vlopatka.engine.helper.FieldHelper.setValueToObject
import com.vlopatka.engine.objectConfigurator.ObjectConfigurator
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

class InjectionAnnotationObjectConfigurator : ObjectConfigurator {

    @ExperimentalStdlibApi
    override fun configure(obj: Any, context: ApplicationContext) {
        obj::class.memberProperties
            .filter { it.hasAnnotation<Injection>() }
            .forEach { setValueToObject(obj, it, context.getObject(it.returnType.javaType as Class<*>)) }
    }
}