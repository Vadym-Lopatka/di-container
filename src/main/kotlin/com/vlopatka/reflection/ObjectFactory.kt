package com.vlopatka.reflection

import com.vlopatka.annotation.InjectProperty
import com.vlopatka.reflection.config.KotlinConfig
import com.vlopatka.reflection.objectConfigurator.InjectPropertyAnnotationObjectConfigurator
import com.vlopatka.service.notifier.Notifier
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

object ObjectFactory {

    private val config = KotlinConfig(
        packageToScan = "com.vlopatka",
        /**
         * I have several implementations of SecurityService
         * and here I am configuring which particular
         * implementation I want my DI container to inject under SecurityService interface
         * @see com.vlopatka.service.security.SecurityService
         */
        interfaceToImplementationMap = mapOf(
            SecurityService::class.java to OutdoorSecurityService::class.java
        )
    )

    private val configurator =  InjectPropertyAnnotationObjectConfigurator()

    fun <T> createObject(type: Class<T>): T {
        return if (type.isInterface) {
            buildObject(config.getImplClass(type))
        } else {
            buildObject(type)
        }
    }

    private fun <T> buildObject(implClass: Class<T>): T {
        val createdObject = implClass.declaredConstructors.first().newInstance()
        configurator.configure(createdObject)

        return createdObject as T
    }

}
