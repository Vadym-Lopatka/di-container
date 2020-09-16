package com.vlopatka.reflection.config

import org.reflections.Reflections

class YmlConfig(packageToScan: String) : Config {
    private val scanner: Reflections = Reflections(packageToScan)

    override fun <T> getImplClass(ifc: Class<T>): Class<T> {
        return scanner.getSubTypesOf(ifc).takeIf { it.size == 1 }
            ?.let { it.first() as Class<T> }
            ?: run { throw RuntimeException("$ifc has 0 or more than 1 implementations") }
    }
}
