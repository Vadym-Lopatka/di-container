package com.vlopatka.reflection

import com.vlopatka.annotation.InjectFromConfig
import com.vlopatka.reflection.config.KotlinConfig
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

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

    private fun <T> buildObject(implClass: Class<T>): Any {
        var t = implClass.declaredConstructors.first().newInstance()

        for(member in t::class.declaredMemberProperties) {
            if(member.hasAnnotation<InjectFromConfig>()) {
                val table = member.annotations.find { it is InjectFromConfig } as? InjectFromConfig
                val configurationPropertyName = table?.value?.takeIf { it.isNotEmpty() } ?: member.name

                val propertiesMap = getMapFrom("application.properties")

                // todo set property from config to the field
                println(propertiesMap)
            }
        }

        return t
    }

    private fun getMapFrom(s: String): Map<String,String> {
        return ClassLoader.getSystemClassLoader().getResource("application.properties")
            .readText()
            .lines()
            .map { it.split("=") }
            .associateBy({it[0]}, {it[1]})
    }
}