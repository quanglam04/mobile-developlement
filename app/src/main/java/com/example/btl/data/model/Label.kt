package com.example.btl.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labels")
data class Label(
    @PrimaryKey
    val id: String,

    val name: String,

    val color: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
