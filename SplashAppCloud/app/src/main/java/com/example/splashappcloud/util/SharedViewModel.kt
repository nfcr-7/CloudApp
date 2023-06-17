package com.example.splashappcloud.util

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(): ViewModel(){

    fun saveData(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {

        val firestoreRef = Firebase.firestore
            .collection("user")
            .document(userData.UserID)

        try {

            firestoreRef.set(userData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data project", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    fun retrieveData(
        userID: String,
        context: Context,
        data : (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val firestoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {

            firestoreRef.get()
                .addOnSuccessListener {
                    if(it.exists()) {
                        val userData = it.toObject<UserData>()!!
                        data(userData)
                    } else {
                        Toast.makeText(context, "No Project Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    fun deleteData(
        userID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val firestoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {

            firestoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully delete data", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }


}