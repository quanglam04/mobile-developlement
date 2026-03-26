package com.example.btl.data.model

enum class RecurrenceRule(val value: String) {
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY");

    companion object {
        fun fromValue(value: String?): RecurrenceRule? {
            if (value == null) return null
            return entries.find { it.value == value }
        }
    }
}