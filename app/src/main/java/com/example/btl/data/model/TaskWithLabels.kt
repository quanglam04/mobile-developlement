package com.example.btl.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Quan hệ nhiều-nhiều giữa Task và Label thông qua bảng trung gian task_labels.
 * Dùng khi cần query một Task kèm theo danh sách Label của nó.
 */
data class TaskWithLabels(
    @Embedded
    val task: Task,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskLabel::class,
            parentColumn = "task_id",
            entityColumn = "label_id"
        )
    )
    val labels: List<Label>
)