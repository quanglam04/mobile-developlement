package com.example.btl.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_reminders",
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["task_id"]),
        Index(value = ["scheduled_at", "is_sent"])
    ]
)
data class TaskReminder(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "task_id")
    val taskId: String,

    @ColumnInfo(name = "minutes_before")
    val minutesBefore: Int,

    @ColumnInfo(name = "is_sent")
    val isSent: Boolean = false,

    @ColumnInfo(name = "scheduled_at")
    val scheduledAt: Long
)