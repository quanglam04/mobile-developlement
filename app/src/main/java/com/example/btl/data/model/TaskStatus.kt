package com.example.btl.data.model

enum class TaskStatus(val value: String) {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    DELAYED("DELAYED");

    companion object {
        fun fromValue(value: String): TaskStatus {
            return entries.find { it.value == value } ?: TODO
        }
    }
}