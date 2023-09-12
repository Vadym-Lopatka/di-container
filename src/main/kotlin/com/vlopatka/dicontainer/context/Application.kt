package com.vlopatka.dicontainer.context

import com.vlopatka.dicontainer.config.KotlinConfig
import com.vlopatka.dicontainer.factory.ObjectFactory

object Application {

    fun run(packageToScan: String, interfaceToImplementationMap: Map<Class<*>, Class<*>>): ApplicationContext {
        val config = KotlinConfig(packageToScan, interfaceToImplementationMap)
        val context = ApplicationContext(config)

        val factory = ObjectFactory(context)
        context.factory = factory

        return context
    }
}