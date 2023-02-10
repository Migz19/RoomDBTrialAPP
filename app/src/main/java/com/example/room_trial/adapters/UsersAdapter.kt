package com.example.room_trial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room_trial.R
import com.example.room_trial.model.UserModel


class UsersAdapter: ListAdapter<UserModel, UsersAdapter.UserViewHolder>(DiffCallback()) {
    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(item: UserModel) = with(itemView) {
                // TODO: Bind the data with View

                setOnClickListener {
                    // TODO: Handle on click
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      holder.bind(getItem(position))
    }
}
