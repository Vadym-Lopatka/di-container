package com.vlopatka.dicontainer.objectConfigurator.impls

import com.vlopatka.dicontainer.annotation.Injection
import com.vlopatka.dicontainer.context.ApplicationContext
import com.vlopatka.dicontainer.helper.FieldHelper.setValueToObject
import com.vlopatka.dicontainer.objectConfigurator.ObjectConfigurator
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