package com.vlopatka.engine.config

import org.reflections.Reflections

class KotlinConfig(packageToScan: String, interfaceToImplementationMap: Map<Class<*>, Class<*>>) : Config {
    private val scanner: Reflections = Reflections(packageToScan)
    private val interfaceToImplementationMap = interfaceToImplementationMap

    override fun <T> getImplClass(ifc: Class<T>): Class<T> {
        return interfaceToImplementationMap.getOrElse(ifc, defaultValue = {

            return scanner.getSubTypesOf(ifc).takeIf { it.size == 1 }
                ?.let { it.first() as Class<T> }
                ?: run { throw RuntimeException("$ifc has 0 or more than 1 implementations") }

        }) as Class<T>
    }

    override fun getScanner() = scanner
}
