package com.whgarcia.mtodobiseccin.model

data class BisecctionModel(
    val function : String = "x^3+3x^2+12x+8",
    val x0 : String = "-5",
    val x1 : String = "5",
    val tolerance : String = "0.0001",
    val type_tolerance : Int = 0,
    val precision: String = "4",
    val bisectioResult: Double = 0.0
)
