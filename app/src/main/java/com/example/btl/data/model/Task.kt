package com.example.btl.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: String,

    val title: String,

    val description: String? = null,

    val priority: Int = Priority.MEDIUM.value,

    val status: String = TaskStatus.TODO.value,

    @ColumnInfo(name = "due_date")
    val dueDate: Long? = null,

    @ColumnInfo(name = "is_recurring")
    val isRecurring: Boolean = false,

    @ColumnInfo(name = "recurrence_rule")
    val recurrenceRule: String? = null,

    @ColumnInfo(name = "completed_at")
    val completedAt: Long? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
)
