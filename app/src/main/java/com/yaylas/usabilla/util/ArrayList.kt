package com.yaylas.usabilla.util

fun ArrayList<String>.toPrintableString() : String {
    var result = ""
    for(str in this) {
        if(result.isNotEmpty()) {
            result += " "
        }
        result += "$str,"
    }
    if(result.isNotEmpty()) {
        result = result.substring(0, result.length - 1)
    }
    return result
}