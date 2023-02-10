package com.example.room_trial.repositories.network

import com.example.room_trial.model.UserModel
//Domain Layer
//For trial only with no implementations
interface UsersApiService {
    fun deleteUserfromRemote(user: UserModel)
    {
        println("deleted")
    }
    fun addUserToRemote(user: UserModel)
    {
        println("added")
    }
    fun updateUserToRemote(user: UserModel){
        println("updated")
    }
}