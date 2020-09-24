package com.vlopatka.reflection.config

import org.reflections.Reflections

interface Config {
    fun <T> getImplClass(ifc: Class<T>): Class<T>
    fun getScanner(): Reflections
}
