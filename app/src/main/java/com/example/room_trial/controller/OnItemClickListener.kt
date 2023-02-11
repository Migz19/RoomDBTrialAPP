package com.example.room_trial.controller

import com.example.room_trial.model.UserModel

interface OnItemClickListener {
    fun deleteItem(user:UserModel)
    fun updateItem(user: UserModel)
}