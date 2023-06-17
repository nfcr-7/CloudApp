package com.example.splashappcloud.screen

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.splashappcloud.ui.theme.colorTextField
import com.example.splashappcloud.ui.theme.whiteBackground
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListDataScreen(navController: NavHostController) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }


    val firestoreRef = Firebase.firestore
    val users = remember {
        mutableStateListOf<DocumentSnapshot>()
    }

    firestoreRef.collection("user")
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
                users.add(document)
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "Error getting documents: ", exception)
        }

    LazyColumn (modifier = Modifier.padding(20.dp)){

        stickyHeader {
            // back button
            Row(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxWidth()
                    .background(whiteBackground),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back_button")
                }
            }
        }

        items(users){user ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(text = "Id of project: ${user["userID"]}")
                        Text(text = "Project Name: ${user["projectName"]}")
                        Text(text = "Project Description: ${user["projectDescription"]}")

                        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
                            // Récupérer l'URI de l'image sélectionnée
                            result.data?.data?.let { uri ->
                                imageUri = uri
                            }
                        }
                        Row {
                            Text(text = "Project: ")
                            ClickableText(text = AnnotatedString("${user["project"]}"), style = TextStyle(
                                color = colorTextField
                            ) ,onClick = { val intent = Intent(
                                Intent.ACTION_GET_CONTENT).apply {
                                type = "*/*"
                            }
                                launcher.launch(intent)} )
                        }
                        Text(text = "Project Language: ${user["projectLanguage"]}")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ListDataScreenpreview(){
    ListDataScreen(rememberNavController())
}