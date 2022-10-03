package com.fictivestudios.demoarcitectureapp.data.model.response.user

data class Users(
    var limit: Int?,
    var skip: Int?,
    var total: Int?,
    var users: ArrayList<User>?
)