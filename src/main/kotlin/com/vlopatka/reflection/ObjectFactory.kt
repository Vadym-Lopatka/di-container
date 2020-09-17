package com.vlopatka.reflection

import com.vlopatka.reflection.config.YmlConfig
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService

object ObjectFactory {

    private val config =
        YmlConfig("com.vlopatka", mapOf(SecurityService::class.java to OutdoorSecurityService::class.java))

    fun <T> createObject(type: Class<T>): T {
        return if (type.isInterface) {
            buildObject(config.getImplClass(type))
        } else {
            buildObject(type)
        }
    }

    private fun <T> buildObject(implClass: Class<T>): T {
        return implClass.declaredConstructors.first().newInstance() as T
    }
}