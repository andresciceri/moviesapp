package com.example.base.extensions

import java.security.MessageDigest

fun String.toSHA256(): String {
    val bytes = this.toByteArray()
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val digest = messageDigest.digest(bytes)
    return digest.fold("", { str, it -> str + "%02x".format(it) })
}