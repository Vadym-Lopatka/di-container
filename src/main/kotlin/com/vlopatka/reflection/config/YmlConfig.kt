package com.vlopatka.reflection.config

import org.reflections.Reflections

class YmlConfig(packageToScan: String, ifc2ImplClass: Map<Class<*>, Class<*>>) : Config {
    private val scanner: Reflections = Reflections(packageToScan)
    private val ifc2ImplClass = ifc2ImplClass

    override fun <T> getImplClass(ifc: Class<T>): Class<T> {
        return ifc2ImplClass.getOrElse(ifc, defaultValue = {

            return scanner.getSubTypesOf(ifc).takeIf { it.size == 1 }
                ?.let { it.first() as Class<T> }
                ?: run { throw RuntimeException("$ifc has 0 or more than 1 implementations") }

        }) as Class<T>
    }
}
