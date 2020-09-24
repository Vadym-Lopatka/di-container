package com.vlopatka.service.security

class OutdoorSecurityService : SecurityService {
    override fun safetyCheck() {
        println("The area around spaceport is safe")
    }
}