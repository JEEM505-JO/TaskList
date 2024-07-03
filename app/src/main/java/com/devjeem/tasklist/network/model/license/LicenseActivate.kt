package com.devjeem.tasklist.network.model.license

data class LicenseActivate(
    val device_id: String = "device_id",
    val device_information: String = "Device information",
    val hostname: String = "Hostname",
    val serial: String = "1234567890"
)