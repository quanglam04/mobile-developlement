package com.example.btl.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "task_labels",
    primaryKeys = ["task_id", "label_id"],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Label::class,
            parentColumns = ["id"],
            childColumns = ["label_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["label_id"])
    ]
)
data class TaskLabel(
    @ColumnInfo(name = "task_id")
    val taskId: String,

    @ColumnInfo(name = "label_id")
    val labelId: String
)