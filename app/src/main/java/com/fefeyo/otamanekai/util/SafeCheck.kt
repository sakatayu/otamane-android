package com.fefeyo.otamanekai.util

fun <T1: Any, T2: Any> safeLet(p1: T1?, p2: T2?): Boolean = p1 != null && p2 != null
