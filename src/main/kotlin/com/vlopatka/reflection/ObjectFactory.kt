package com.vlopatka.reflection

import com.vlopatka.reflection.config.KotlinConfig
import com.vlopatka.reflection.objectConfigurator.ObjectConfigurator
import com.vlopatka.service.security.OutdoorSecurityService
import com.vlopatka.service.security.SecurityService

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

    private val configurators: List<ObjectConfigurator> = initConfigurators()

    fun <T> createObject(type: Class<T>): T {
        return if (type.isInterface) {
            buildObject(config.getImplClass(type))
        } else {
            buildObject(type)
        }
    }

    private fun <T> buildObject(implClass: Class<T>): T {
        val createdObject = implClass.declaredConstructors.first().newInstance()
        configurators.forEach { it.configure(createdObject) }

        return createdObject as T
    }

    private fun initConfigurators(): List<ObjectConfigurator> {
        val objConfiguratorImplementations = config.getScanner().getSubTypesOf(ObjectConfigurator::class.java)

        return objConfiguratorImplementations
            .map { it.declaredConstructors.first().newInstance() as ObjectConfigurator }
            .toList()
    }
}
