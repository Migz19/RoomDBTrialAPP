package com.example.room_trial.data.db.typeconverters

import androidx.room.TypeConverter
import java.util.*

class dateLongConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}