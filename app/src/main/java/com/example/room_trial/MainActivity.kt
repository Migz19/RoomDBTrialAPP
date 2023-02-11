package com.example.room_trial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room_trial.adapters.UsersAdapter
import com.example.room_trial.controller.UserController
import com.example.room_trial.db.UserDatabase
import com.example.room_trial.model.UserModel
import com.example.room_trial.repositories.local.UsersRepoImpl

class MainActivity : AppCompatActivity() {
    private val usersDatabase: UserDatabase by lazy {
        UserDatabase.getDatabase(baseContext)
    }
    private val userController: UserController by lazy {
       UserController(usersDatabase.userDao())
    }
    private val adapter = UsersAdapter()
    private var list: ArrayList<UserModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}