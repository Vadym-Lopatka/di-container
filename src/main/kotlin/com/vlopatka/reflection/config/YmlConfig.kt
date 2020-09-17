package com.vlopatka.reflection.config

import org.reflections.Reflections

class YmlConfig(packageToScan: String, interfaceToImplementationMap: Map<Class<*>, Class<*>>) : Config {
    private val scanner: Reflections = Reflections(packageToScan)
    private val interfaceToImplementationMap = interfaceToImplementationMap

    override fun <T> getImplClass(ifc: Class<T>): Class<T> {
        return interfaceToImplementationMap.getOrElse(ifc, defaultValue = {

            return scanner.getSubTypesOf(ifc).takeIf { it.size == 1 }
                ?.let { it.first() as Class<T> }
                ?: run { throw RuntimeException("$ifc has 0 or more than 1 implementations") }

        }) as Class<T>
    }
}
