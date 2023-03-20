package com.ilhomsoliev.affirmations.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey
    val id:Int?=null,
    val name:String,
)
