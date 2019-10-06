package com.example.sbppesawat5

import java.io.Serializable

data class Plane (
    val id: Int,
    var name: String,
    var characteristics: MutableList<Characteristic>): Serializable