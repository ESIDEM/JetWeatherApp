package com.xtremepixel.jetweatherapp.data

class ResultWrapper<T, Boolean, E: Exception> (

    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var error: E? = null
    )