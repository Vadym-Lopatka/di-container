package com.vlopatka.engine.objectConfigurator

import com.vlopatka.engine.context.ApplicationContext

interface ObjectConfigurator {
    fun configure(obj: Any, context: ApplicationContext)
}