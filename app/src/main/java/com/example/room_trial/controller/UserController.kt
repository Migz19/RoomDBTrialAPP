package com.example.room_trial.controller

import com.example.room_trial.db.UserDao
import com.example.room_trial.model.UserModel
import com.example.room_trial.repositories.local.UsersRepoImpl

class UserController(userDao: UserDao){
    private val usersRepo=UsersRepoImpl(userDao)
    private var usersList=ArrayList<UserModel>()
    fun getUsers(): ArrayList<UserModel> {
        usersList=usersRepo.getAllUsers()
        return usersList
    }
    fun getUserByGender(gender: Char){
        val c: Char? = when (gender){
            'm','M' ->'M'
            'f','F'->'F'
            else -> null
        }
        usersRepo.getUsersByGender(c)
    }
    fun addUser(user:UserModel){
        //we can check about user valid data before adding like gender is valid or not
        //and some other validation as email and password strength
        validPassword(user.password)
        usersRepo.addUserLocal(user)
    }
    private fun validPassword(password:String?):Boolean{
        for (user in usersList){
           if (user.password==password)
               return false
        }
       return true
    }
    fun deleteUser(user:UserModel){
        //We can check if user is not admin or sth like that before deleting
        //Use one delete function may be easies
        usersRepo.deleteUserById(user.id)
    }
    fun updateUser(newUser:UserModel){
        for ( oldUser in usersList ){
            if (newUser.id==oldUser.id)
                usersRepo.updateUserLocal(newUser)
        }
    }
    fun clearDb(){
        if (usersList.isNotEmpty())
            usersRepo.clearDb()
    }
}