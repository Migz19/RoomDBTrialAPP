package com.example.room_trial

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_trial.adapters.UsersAdapter
import com.example.room_trial.controller.OnItemClickListener
import com.example.room_trial.controller.UserController
import com.example.room_trial.databinding.ActivityMainBinding
import com.example.room_trial.databinding.CustomDialogueboxBinding
import com.example.room_trial.data.db.UserDatabase
import com.example.room_trial.model.UserModel
import com.example.room_trial.repositories.local.UsersRepoImpl

class MainActivity : AppCompatActivity(), OnItemClickListener {


    private lateinit var binding: ActivityMainBinding

    private lateinit var dialogbinding: CustomDialogueboxBinding

    private val usersDatabase: UserDatabase by lazy {
        UserDatabase.getDatabase(baseContext)
    }

    private val userController: UserController by lazy {
        UserController(usersDatabase.userDao())
    }
    private lateinit var usersAdapter: UsersAdapter
    private var list: ArrayList<UserModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogbinding = CustomDialogueboxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = userController.getUsers()
        usersAdapter = UsersAdapter(list!!, this)


        val usersRv = binding.recyclerView
        usersRv.layoutManager = LinearLayoutManager(this)
        usersRv.adapter = usersAdapter

        binding.clearBtn.setOnClickListener {
            userController.clearDb()
        }
        binding.insertBtn.setOnClickListener {
            val user = showDialogBox(null)
            insertNewItem(user)
        }
        binding.deleteBtn.setOnClickListener {
            userController.addUser(UserModel("ahmed", "gmail", password = "kdfjfjsdkjf", gender = 'c'))
            userController.addUser(UserModel("mazen", "gmail", password = "kdfjfjsdkjf", gender = 'c'))
            userController.addUser(UserModel("mazen", "gmail", password = "kdfjfjsdkjf", gender = 'c'))
            userController.addUser(UserModel("migz", "gmail", password = "kdfjfjsdkjf", gender = 'c'))
            userController.addUser(UserModel("kamal", "gmail", password = "kdfjfjsdkjf", gender = 'c'))
        }
    }

    private fun insertNewItem(user: UserModel?) {
        if (user != null)
            userController.addUser(user)
    }

    override fun deleteItem(user: UserModel) {
        userController.deleteUser(user)
    }

    override fun updateItem(user: UserModel) {
        val newUser = showDialogBox(user)
        dialogbinding.idTextView.visibility = View.VISIBLE
        if (newUser?.email == user.email && newUser?.name == user.name)
            return
        try {
            userController.updateUser(newUser!!)
        } catch (e: java.lang.NullPointerException) {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialogBox(user: UserModel?): UserModel? {

        val dialog = Dialog(this)
        dialog.create()
        dialog.show()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialoguebox)
        if (user != null) {
            dialogbinding.emailEditTxt.hint = user.email
            dialogbinding.genderEditTxt.hint = user.gender.toString()
            dialogbinding.nameEditTxt.hint = user.name
            dialogbinding.idTextView.text = user.id.toString()
        }

        var user: UserModel? = null
        dialogbinding.submitBtnn.setOnClickListener {
            val email = dialogbinding.emailEditTxt.text.toString()
            val name = dialogbinding.nameEditTxt.text.toString()
            val gender: Char = dialogbinding.genderEditTxt.text.toString()[0]
            user = UserModel(email = email, name = name, gender = gender)
            dialog.dismiss()
        }

        return user
    }

    }
