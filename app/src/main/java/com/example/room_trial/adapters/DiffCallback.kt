package com.example.room_trial.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.room_trial.model.UserModel

class DiffCallback : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem==newItem
    }


}