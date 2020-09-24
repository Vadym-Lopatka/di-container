package com.vlopatka.reflection.objectConfigurator

interface ObjectConfigurator {
    fun <T> configure(t: Class<T>)
}