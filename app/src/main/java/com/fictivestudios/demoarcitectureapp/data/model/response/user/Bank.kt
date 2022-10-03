package com.fictivestudios.demoarcitectureapp.data.model.response.user

data class Bank(
    var cardExpire: String?,
    var cardNumber: String?,
    var cardType: String?,
    var currency: String?,
    var iban: String?
)