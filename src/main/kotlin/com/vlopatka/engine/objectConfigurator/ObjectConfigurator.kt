package com.vlopatka.engine.objectConfigurator

import com.vlopatka.engine.application.ApplicationContext

interface ObjectConfigurator {
    fun configure(obj: Any, context: ApplicationContext)
}