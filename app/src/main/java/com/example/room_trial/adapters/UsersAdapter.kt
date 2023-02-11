package com.example.room_trial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.room_trial.controller.OnItemClickListener
import com.example.room_trial.databinding.RecyclerviewBinding
import com.example.room_trial.model.UserModel


class UsersAdapter(var usersList: ArrayList<UserModel>, val listener: OnItemClickListener) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private lateinit var binding: RecyclerviewBinding

    inner class UserViewHolder(binding: RecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        binding = RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(usersList[position]) {
            binding.nameTv.text = name
            binding.emailTv.text = email
            binding.idTv.text = "User id : $id"
            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context, email, Toast.LENGTH_SHORT).show()
            }
            binding.deleteBtn.setOnClickListener {
                listener.deleteItem(usersList[position])
            }
            binding.updateBtn.setOnClickListener {
                listener.updateItem(usersList[position])
            }
        }
    }

    override fun getItemCount(): Int = usersList.size

}
