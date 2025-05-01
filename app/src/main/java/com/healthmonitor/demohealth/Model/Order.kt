package com.healthmonitor.demohealth.Model

import java.util.*

data class Order(
    val type: String,
    val quantity: Int,
    val info1: String,
    val info2: String,
    val info3: String,
    val timestamp: Date = Date()
)