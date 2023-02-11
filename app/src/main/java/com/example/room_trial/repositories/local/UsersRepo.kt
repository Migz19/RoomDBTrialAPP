package com.example.room_trial.repositories.local

import com.example.room_trial.db.UserDao
import com.example.room_trial.model.UserModel
import com.example.room_trial.repositories.network.UsersApiService

//Domain Layer
interface UsersRepo : UserDao, UsersApiService {
    override fun addUserLocal(user: UserModel)

    override fun deleteUserLocal(user: UserModel)

    override fun updateUserLocal(user: UserModel)


    override fun getAllUsers(): List<UserModel>

    override fun getUsersByGender(gender: Char?): List<UserModel>

    override fun deleteUserById(user_id: Long)
    override fun clearDb()

    /////////////////////////////////////////////////
    override fun deleteUserfromRemote(user: UserModel) {
        super.deleteUserfromRemote(user)
    }

    override fun addUserToRemote(user: UserModel) {
        super.addUserToRemote(user)
    }

    override fun updateUserToRemote(user: UserModel) {
        super.updateUserToRemote(user)
    }
}