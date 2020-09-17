package com.vlopatka.service.security

import com.vlopatka.service.security.SecurityService

class OutdoorSecurityService : SecurityService {
    override fun safetyCheck() {
        println("The area around spaceport is safe")
    }
}