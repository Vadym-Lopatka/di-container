package com.vlopatka.reflection

import com.vlopatka.annotation.InjectFromConfig
import com.vlopatka.reflection.config.KotlinConfig
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

    fun <T> createObject(type: Class<T>): T {
        return if (type.isInterface) {
            buildObject(config.getImplClass(type)) as T
        } else {
            buildObject(type) as T
        }
    }

    private fun <T> buildObject(implClass: Class<T>): T {
        val thObject = implClass.declaredConstructors.first().newInstance()
        val configData = getMapFrom("application.properties")

        for (field in thObject::class.memberProperties) {
            if (field.hasAnnotation<InjectFromConfig>()) {
                val theAnnotation = field.annotations.find { it is InjectFromConfig } as? InjectFromConfig
                val fieldName = theAnnotation?.value?.takeIf { it.isNotEmpty() } ?: field.name

                setValueToField(thObject,field, configData[fieldName])
            }
        }

        return thObject as T
    }

    private fun setValueToField(thObject: Any, field: KProperty1<out Any, *>, value: String?) {
        val property = thObject::class.memberProperties.find { it.name == field.name }
        if (property is KMutableProperty<*>) {
            property.isAccessible = true
            property.setter.call(thObject, value)
        }
    }


    private fun getMapFrom(s: String): Map<String,String> {
        return ClassLoader.getSystemClassLoader().getResource("application.properties")
            .readText()
            .lines()
            .map { it.split("=") }
            .associateBy({it[0]}, {it[1]})
    }
}
