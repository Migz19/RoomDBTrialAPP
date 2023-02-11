package com.example.room_trial

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_trial.adapters.UsersAdapter
import com.example.room_trial.controller.OnItemClickListener
import com.example.room_trial.controller.UserController
import com.example.room_trial.databinding.ActivityMainBinding
import com.example.room_trial.databinding.CustomDialogueboxBinding
import com.example.room_trial.db.UserDatabase
import com.example.room_trial.model.UserModel

class MainActivity : AppCompatActivity(), OnItemClickListener {


    private lateinit var binding: ActivityMainBinding
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
        setContentView(binding.root)
        val usersRv = binding.recyclerView
        usersRv.apply {
            LinearLayoutManager(context)
        }
        list = userController.getUsers()
        usersAdapter = UsersAdapter(list!!, this)
        binding.clearBtn.setOnClickListener {
            userController.clearDb()
        }
        binding.insertBtn.setOnClickListener {
            val user = showDialogBox(null)
            insertNewItem(user)
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
        if (newUser?.email == user.email && newUser?.name == user.name)
            return
        try {
            userController.updateUser(newUser!!)
        } catch (e: java.lang.NullPointerException) {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialogBox(user: UserModel?): UserModel? {
        val binding = CustomDialogueboxBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialoguebox)
        if (user != null) {
            binding.emailEditTxt.hint = user.email
            binding.genderEditTxt.hint = user.gender.toString()
            binding.nameEditTxt.hint = user.name
            binding.idTextView.text = user.id.toString()
        }
        val email = binding.emailEditTxt.text.toString()
        val name = binding.nameEditTxt.text.toString()
        val gender: Char = binding.genderEditTxt.text.toString()[0]
        var user: UserModel? = null
        binding.submitBtnn.setOnClickListener {
            user = UserModel(email = email, name = name, gender = gender)
            dialog.dismiss()
        }
        dialog.show()
        return user
    }
}