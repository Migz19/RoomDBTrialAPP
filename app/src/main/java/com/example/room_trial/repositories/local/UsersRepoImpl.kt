package com.example.room_trial.repositories.local

import com.example.room_trial.data.db.UserDao
import com.example.room_trial.model.UserModel

//Data Layer
class UsersRepoImpl(private val userDao: UserDao) : UsersRepo {
    override fun addUserLocal(user: UserModel) {
        userDao.addUserLocal(user)
    }

    override fun deleteUserLocal(user: UserModel) {
        userDao.deleteUserLocal(user)
    }

    override fun updateUserLocal(user: UserModel) {
        userDao.updateUserLocal(user)
    }

    override fun getAllUsers(): ArrayList<UserModel> = userDao.getAllUsers() as ArrayList
    //Checks if character is null returns empty list, if not returns list from database
    override fun getUsersByGender(gender: Char?): ArrayList<UserModel> {
        var usersList = ArrayList<UserModel>()
        if (gender != null) {
            usersList = userDao.getUsersByGender(gender) as ArrayList<UserModel>
        }
        return usersList
    }

    override fun deleteUserById(user_id: Long) {
        userDao.deleteUserById(user_id)
    }

    override fun clearDb() {
        userDao.clearDb()
    }


}