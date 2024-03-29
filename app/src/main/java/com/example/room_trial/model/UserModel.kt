package com.example.room_trial.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Users") //if not specified default table name will be same as entity class name
data class UserModel(
    @ColumnInfo(defaultValue = "username")
    var name: String? = null,
    @ColumnInfo(defaultValue = "msp@gmail.com")
    var email: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long=0, //Already defined as non null
    var password: String? = null,
    var gender: Char? = null,
    @Ignore
    var phoneNumber: String? = "122154151515",
)

