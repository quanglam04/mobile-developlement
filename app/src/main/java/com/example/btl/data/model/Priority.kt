package com.example.btl.data.model

enum class Priority(val value: Int) {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    companion object {
        fun fromValue(value: Int): Priority {
            return entries.find { it.value == value } ?: MEDIUM
        }
    }
}