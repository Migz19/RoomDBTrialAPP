package com.example.room_trial.data.db

import androidx.room.*
import com.example.room_trial.model.UserModel

//Domain Layer
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    /* Primary key CONFLICT
    // Replace --> Replace old block by new one
    //Ignore --> Neglect new insertion
    //Abort --> Default option , Room will throw a SQLiteConstraintException
    None --> malhash lazma w fl runtime btt3aml zy el ABORT wl default
    ("Ahmed","Ahmed@gmail",12345)
   ("ali","ali@gmail",12345)
     */
    fun addUserLocal(user: UserModel)

    @Update //Used to update whole entity block
    fun updateUserLocal(user: UserModel)

    @Delete
    fun deleteUserLocal(user: UserModel) //Delete whole block

    @Query("select *from users order by id ")
    fun getAllUsers(): List<UserModel>

    //get list of users by specific attribute
    @Query("select * from users where gender =:gender order by id")
    fun getUsersByGender(gender: Char?): List<UserModel>

    //Delete user by specific attribute

    @Query("delete from users where id=:user_id")
    fun deleteUserById(user_id: Long)
    @Query("delete from users")
    fun clearDb()
}
