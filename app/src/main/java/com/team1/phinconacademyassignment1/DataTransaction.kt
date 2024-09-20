package com.team1.phinconacademyassignment1

data class DataTransaction(
    val typeTransaction: String,
    val numberOfTransaction: Long,
    val description:String,
    val date: String?,
    val lastBalance:Long?
)
