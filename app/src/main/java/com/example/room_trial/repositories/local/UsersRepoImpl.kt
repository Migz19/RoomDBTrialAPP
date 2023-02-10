package com.example.room_trial.repositories.local

import com.example.room_trial.db.UserDao
import com.example.room_trial.model.UserModel

//Data Layer
class UsersRepoImpl(private val userDao: UserDao) : UsersRepo {
    override fun addUserLocal(user: UserModel) {
        userDao.addUserLocal(user)
    }

    override fun deleteUserLocal(user: UserModel) {
        userDao.updateUserLocal(user)
    }

    override fun updateUserLocal(user: UserModel) {
        userDao.deleteUserLocal(user)
    }

    override fun getAllUsers(): ArrayList<UserModel> = userDao.getAllUsers() as ArrayList
    override fun getUsersByGender(gender: Char): Any? {
        return when (gender) {
            'm', 'M' -> userDao.getUsersByGender('m')
            'f', 'F' -> userDao.getUsersByGender('f')
            else -> null
        }

    }

    override fun deleteUserByName(username: String) = userDao.deleteUserByName(username)

}