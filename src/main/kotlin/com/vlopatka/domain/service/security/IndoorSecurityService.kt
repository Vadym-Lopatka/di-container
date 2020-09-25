package com.vlopatka.domain.service.security

class IndoorSecurityService : SecurityService {
    override fun safetyCheck() {
        println("All windows and doors are closed. Office can be taken under guard.")
    }
}