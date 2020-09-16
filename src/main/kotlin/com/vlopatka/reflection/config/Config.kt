package com.vlopatka.reflection.config

interface Config {
    fun <T> getImplClass(ifc: Class<T>): Class<T>
}
