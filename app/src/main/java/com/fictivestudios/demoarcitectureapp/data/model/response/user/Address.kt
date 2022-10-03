package com.fictivestudios.demoarcitectureapp.data.model.response.user

data class Address(
    var address: String?,
    var city: String?,
    var coordinates: Coordinates?,
    var postalCode: String?,
    var state: String?
)