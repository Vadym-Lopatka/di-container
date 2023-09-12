package com.vlopatka.dicontainer.objectConfigurator

import com.vlopatka.dicontainer.context.ApplicationContext

interface ObjectConfigurator {
    fun configure(obj: Any, context: ApplicationContext)
}