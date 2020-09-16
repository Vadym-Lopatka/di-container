package com.vlopatka.reflection

import com.vlopatka.reflection.config.Config
import com.vlopatka.reflection.config.YmlConfig

object ObjectFactory {

    private val config: Config = YmlConfig("com.vlopatka")

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