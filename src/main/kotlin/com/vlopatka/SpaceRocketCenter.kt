package com.vlopatka

class SpaceRocketCenter {
    fun manageLaunch(rocket: Rocket): Unit {
        // todo: notify all spaceports staff that we are performing launch
        // todo: check that area is safe
        val results = launch(rocket)
        // todo: notify all spaceports staff about launch results
    }

    private fun launch(rocket: Rocket): String {
        TODO("Not yet implemented")
    }
}