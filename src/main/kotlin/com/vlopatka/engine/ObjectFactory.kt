package com.vlopatka.engine

import com.vlopatka.engine.application.ApplicationContext
import com.vlopatka.engine.objectConfigurator.ObjectConfigurator

class ObjectFactory(
    private val context: ApplicationContext
) {
    private val configurators: List<ObjectConfigurator> = initConfigurators()

    fun <T> createObject(type: Class<T>): T {
        /*return if (type.isInterface) {
            buildObject(config.getImplClass(type))
        } else {
            buildObject(type)
        }*/

        return buildObject(type)
    }

    private fun <T> buildObject(implClass: Class<T>): T {
        val obj = implClass.declaredConstructors.first().newInstance()

        /**
         * Here I used a chain of responsibility pattern.
         *
         * Every DI component that would be created through @Injection
         * will be processed once(on startup) by each implementation of the ObjectConfigurator.
         *
         * @see com.vlopatka.annotation.Injection
         * @see com.vlopatka.engine.objectConfigurator.ObjectConfigurator
         */
        configurators.forEach { it.configure(obj, context) }

        return obj as T
    }

    private fun initConfigurators(): List<ObjectConfigurator> {
        val objConfiguratorImplementations = context.config.getScanner().getSubTypesOf(ObjectConfigurator::class.java)

        return objConfiguratorImplementations
            .map { it.declaredConstructors.first().newInstance() as ObjectConfigurator }
            .toList()
    }
}
